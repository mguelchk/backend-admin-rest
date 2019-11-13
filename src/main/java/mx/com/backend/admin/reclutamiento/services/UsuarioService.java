package mx.com.backend.admin.reclutamiento.services;

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

import mx.com.backend.admin.reclutamiento.dao.entity.Menu;
import mx.com.backend.admin.reclutamiento.dao.entity.Rol;
import mx.com.backend.admin.reclutamiento.dao.entity.Usuario;
import mx.com.backend.admin.reclutamiento.dao.menu.IMenuDao;
import mx.com.backend.admin.reclutamiento.dao.rol.IRolDao;
import mx.com.backend.admin.reclutamiento.dao.usuario.IUsuarioDao;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IRolDao rolDao;

	@Autowired
	private IMenuDao menuDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario '" + username + "' en el sistema!");
		}

		usuario.setRoles(rolDao.obtenerRolesPorUsuario(usuario));
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getNombreUsuario(), usuario.getPassword(), usuario.getActivo(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		Usuario usuario  =
				usuarioDao.findByUsername(username);
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
			
			menuPadre.setMenus(obtenerMenusHijos(menusHijo,menuPadre.getIdMenu()));
			
		}
		return menusPadre;
	}

	private List<Menu> obtenerMenusHijos(List<Menu> menusHijo, Long idMenuPadre) {
		List<Menu> listArbolMenu = new ArrayList<>();
		if(menusHijo != null ) {
			for (Menu menu : menusHijo) {
				if(menu.getIdMenuPadre().equals(idMenuPadre)){
					listArbolMenu.add(menu);
					menu.setMenus(obtenerMenusHijos(menusHijo, menu.getIdMenu()));
				}
			}
		}
		return listArbolMenu;
	}

	

}
