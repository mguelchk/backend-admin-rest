package mx.com.backend.admin.reclutamiento.dao.usuario.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.core.exception.SpErrorException;
import mx.com.backend.admin.reclutamiento.dao.mapper.UsuarioMapper;
import mx.com.backend.admin.reclutamiento.dao.sp.UsuarioSP;
import mx.com.backend.admin.reclutamiento.dao.usuario.IUsuarioDao;
import mx.com.backend.admin.reclutamiento.models.Usuario;

@Service
public class UsuarioDao implements IUsuarioDao {

	private final static Logger log = LoggerFactory.getLogger(UsuarioDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Usuario buscarUsuarioPorCorreo(String correo) throws DaoDataAccesException {

		Usuario user;

		try {
			user = jdbcTemplate.queryForObject(
					"SELECT id_usuario,usuario,password,correo,active,recover FROM admin_360.USUARIOS WHERE correo = ? AND active = 1",
					new Object[] { correo }, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			user = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL BUSCAR USUARIO POR NONMBRE ::.. [METODO: buscarUsuarioPorCorreo] " + e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return user;
	}

	@Override
	public Usuario buscarUsuarioPorId(int idUser) throws DaoDataAccesException {

		Usuario user;

		try {
			user = jdbcTemplate.queryForObject(
					"SELECT id_usuario,usuario,password,correo,active,recover FROM admin_360.USUARIOS WHERE id_usuario = ? AND active = 1",
					new Object[] { idUser }, new UsuarioMapper());

		} catch (EmptyResultDataAccessException e) {
			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			user = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL BUSCAR USUARIO POR ID ::.. [METODO: buscarUsuarioPorId] " + e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return user;
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) throws DaoDataAccesException {
		try {
			UsuarioSP sp = new UsuarioSP(jdbcTemplate, "crearUsuario");
			Usuario user = buscarUsuarioPorId(sp.agregarUsuario(usuario));
			return user;

		} catch (SpErrorException e) {
			log.error("..:: ERROR CAPA DAO AL CREAR USUARIO::.. [METODO: crearUsuario] " + e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
	}

	@Override
	public Usuario actualizarRecoverUsuario(Usuario usuario) throws DaoDataAccesException {
		try {
			UsuarioSP sp = new UsuarioSP(jdbcTemplate, "actualizarPasswordTmp");
			Integer d = sp.actualizarRecoverUsuario(usuario);
			return new Usuario();

		} catch (SpErrorException e) {
			log.error("..:: ERROR CAPA DAO ::.. [METODO: actualizarUsuario] " + e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
	}

	@Override
	public Usuario actualizarPassword(Usuario user) throws DaoDataAccesException {
		try {
			UsuarioSP sp = new UsuarioSP(jdbcTemplate, "actualizarPassword");
			Integer d = sp.actualizarPassword(user);
			return new Usuario();

		} catch (SpErrorException e) {
			log.error("..:: ERROR CAPA DAO ::.. [METODO: actualizarUsuario] " + e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
	}

}
