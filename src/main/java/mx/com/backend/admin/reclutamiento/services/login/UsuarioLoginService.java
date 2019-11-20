package mx.com.backend.admin.reclutamiento.services.login;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.backend.admin.reclutamiento.dao.menu.IMenuDao;
import mx.com.backend.admin.reclutamiento.dao.rol.IRolDao;
import mx.com.backend.admin.reclutamiento.dao.usuario.IUsuarioDao;
import mx.com.backend.admin.reclutamiento.models.Menu;
import mx.com.backend.admin.reclutamiento.models.Rol;
import mx.com.backend.admin.reclutamiento.models.Usuario;
import mx.com.backend.admin.reclutamiento.services.login.impl.IUsuarioLoginService;

@Service
public class UsuarioLoginService implements IUsuarioLoginService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioLoginService.class);

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IRolDao rolDao;

	@Autowired
	private IMenuDao menuDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.buscarUsuarioPorCorreo(correo);

		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario '" + correo + "' en el sistema!");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario '" + correo + "' en el sistema!");
		}

		usuario.setRoles(rolDao.obtenerRolesPorUsuario(usuario));
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getEmail(), usuario.getPassword(), usuario.getActivo(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorCorreo(String username) {
		Usuario usuario  =
				usuarioDao.buscarUsuarioPorCorreo(username);
		usuario.setRoles(obtenerRolesPorUsuario(usuario));
		return usuario;
	}

	private List<Rol> obtenerRolesPorUsuario(Usuario usuario) {
		List<Rol> roles = rolDao.obtenerRolesPorUsuario(usuario);
		for (Rol rol : roles) {
			rol.setMenus(obtenerMenusPorRol(rol));
		}
		return roles;
	}

	private List<Menu> obtenerMenusPorRol(Rol rol) {

		List<Menu> menus = menuDao.obtenerMenusPorRol(rol);

		List<Menu> menusPadre = new ArrayList<>();
		List<Menu> menusHijo = new ArrayList<>();

		for (Menu menu : menus) {
			if (menu.getIdMenuPadre() == null) {
				menusPadre.add(menu);
			} else {
				menusHijo.add(menu);
			}
		}
		return menus(menusPadre, menusHijo);
	}

	private List<Menu> menus(List<Menu> menusPadre, List<Menu> menusHijo) {
		
		for (Menu menuPadre : menusPadre) {
			
			menuPadre.setSubMenus(obtenerMenusHijos(menusHijo,menuPadre.getIdMenu()));
			
		}
		return menusPadre;
	}

	private List<Menu> obtenerMenusHijos(List<Menu> menusHijo, Long idMenuPadre) {
		List<Menu> listArbolMenu = new ArrayList<>();
		if(menusHijo != null ) {
			for (Menu menu : menusHijo) {
				if(menu.getIdMenuPadre().equals(idMenuPadre)){
					listArbolMenu.add(menu);
					menu.setSubMenus(obtenerMenusHijos(menusHijo, menu.getIdMenu()));
				}
			}
		}
		return listArbolMenu;
	}

	

}
