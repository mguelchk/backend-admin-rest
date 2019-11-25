package mx.com.backend.admin.reclutamiento.dao.rol.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.dao.rol.IRolDao;
import mx.com.backend.admin.reclutamiento.models.Rol;
import mx.com.backend.admin.reclutamiento.models.Usuario;

@Service
public class RolDao implements IRolDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Rol> obtenerRolesPorUsuario(Usuario usuario){

			List<Rol> roles = jdbcTemplate.query(
					"SELECT 	R.id_rol,R.nombre,R.activo FROM admin_360.USERS_ROLES UR INNER JOIN admin_360.ROLES R ON R.id_rol = UR.id_rol WHERE UR.id_usuario = ? AND R.activo = 1",
					new Object[] { usuario.getIdUsuario() }, (rs, rowNum) -> {

						Rol rol = new Rol();
						rol.setIdRol(rs.getLong("id_rol"));
						rol.setNombre(rs.getString("nombre"));
						return rol;
					});

			return roles;

	}

}
