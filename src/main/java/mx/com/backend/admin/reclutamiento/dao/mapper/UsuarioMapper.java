package mx.com.backend.admin.reclutamiento.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.backend.admin.reclutamiento.models.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

		Usuario use = new Usuario();

		use.setIdUsuario(rs.getLong("id_usuario"));
		use.setPassword(rs.getString("password"));
		use.setNombre(rs.getString("usuario"));
		use.setNombreUsuario(rs.getString("usuario"));
		use.setEmail(rs.getString("correo"));
		use.setActivo(rs.getInt("active") == 1);

		return use;
	}
}
