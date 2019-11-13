package mx.com.backend.admin.reclutamiento.dao.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.dao.entity.Usuario;

@Service
public class UsuarioDao implements IUsuarioDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Usuario findByUsername(String username) {
		Usuario user = jdbcTemplate.queryForObject(
				"SELECT id_usuario,usuario,password,correo,active FROM admin_360.USUARIOS WHERE usuario = ? AND active = 1",
				new Object[] { username }, (rs, rowNum) -> {

					Usuario use = new Usuario();

					use.setIdUsuario(rs.getLong("id_usuario"));
					use.setPassword(rs.getString("password"));
					use.setNombre(rs.getString("usuario"));
					use.setNombreUsuario(rs.getString("usuario"));
					use.setEmail(rs.getString("correo"));
					use.setActivo(rs.getInt("active") == 1);

					return use;
				});
		return user;
	}

	
}
