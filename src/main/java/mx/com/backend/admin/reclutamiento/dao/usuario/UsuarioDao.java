package mx.com.backend.admin.reclutamiento.dao.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.DataAccesException;
import mx.com.backend.admin.reclutamiento.dao.mapper.UsuarioMapper;
import mx.com.backend.admin.reclutamiento.dao.sp.UsuarioSP;
import mx.com.backend.admin.reclutamiento.models.Usuario;

@Service
public class UsuarioDao implements IUsuarioDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Usuario buscarUsuarioPorCorreo(String correo) {
		
		Usuario user ;
		
		try {
			 user = jdbcTemplate.queryForObject(
					"SELECT id_usuario,usuario,password,correo,active FROM admin_360.USUARIOS WHERE correo = ? AND active = 1",
					new Object[] { correo }, new UsuarioMapper());
		}  catch (EmptyResultDataAccessException e) {

			user = null;

		}
		

		return user;

	}

	@Override
	public Usuario buscarUsuarioPorId(int idUser) {

		Usuario user;

		try {
			user = jdbcTemplate.queryForObject(
					"SELECT id_usuario,usuario,password,correo,active FROM admin_360.USUARIOS WHERE id_usuario = ? AND active = 1",
					new Object[] { idUser }, (rs, rowNum) -> {

						Usuario use = new Usuario();

						use.setIdUsuario(rs.getLong("id_usuario"));
						use.setPassword(rs.getString("password"));
						use.setNombre(rs.getString("usuario"));
						use.setNombreUsuario(rs.getString("usuario"));
						use.setEmail(rs.getString("correo"));
						use.setActivo(rs.getInt("active") == 1);

						return use;
					});

		} catch (EmptyResultDataAccessException e) {

			user = null;

		}
		return user;
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) throws DataAccesException {
		try {
			UsuarioSP sp = new UsuarioSP(jdbcTemplate, "crearUsuario");
			Usuario user = buscarUsuarioPorId(sp.agregarUsuario(usuario));
			return user;

		} catch (DataAccessException e) {
			throw new DataAccesException(e);
		}
	}

}
