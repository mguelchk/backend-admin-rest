package mx.com.backend.admin.reclutamiento.dao.menu;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.models.Menu;
import mx.com.backend.admin.reclutamiento.models.Rol;
import mx.com.backend.admin.reclutamiento.services.login.UsuarioLoginService;

@Service
public class MenuDao implements IMenuDao {
	private Logger logger = LoggerFactory.getLogger(UsuarioLoginService.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	

	@Override
	public List<Menu> obtenerMenusPorRol(Rol rol) {
		
		try {
			List<Menu> menus = jdbcTemplate.query(
					"SELECT \n" + 
					"	M.id_menu,\n" + 
					"	M.nombre,\n" + 
					"	M.url,\n" + 
					"    M.icono,\n" + 
					"    M.clase,\n" + 
					"    M.activo,\n" + 
					"    m.id_menu_padre\n" + 
					"FROM admin_360.MENUS_ROLES MR \n" + 
					"INNER JOIN admin_360.MENUS M\n" + 
					"ON MR.id_menu = M.id_menu WHERE MR.id_rol = ? AND M.activo = 1 ;\n" + 
					"",
					new Object[] { rol.getIdRol() }, (rs, rowNum) -> {

						Menu menu = new Menu();
						menu.setIdMenu(rs.getLong("id_menu"));
						menu.setNombre(rs.getString("nombre"));
						menu.setUrl(rs.getString("url"));
						menu.setClase(rs.getString("clase"));
						menu.setIcono(rs.getString("icono"));
						menu.setActivo(rs.getInt("activo")== 1);
						menu.setIdMenuPadre(rs.getLong("id_menu_padre")==0?null:rs.getLong("id_menu_padre"));
						return menu;
					});

			return menus;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info("SWS",e.getCause());
			return null;
		}
		
	}

}
