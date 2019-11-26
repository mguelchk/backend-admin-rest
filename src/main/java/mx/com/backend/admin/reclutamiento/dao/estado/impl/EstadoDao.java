package mx.com.backend.admin.reclutamiento.dao.estado.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.dao.estado.IEstadoDao;
import mx.com.backend.admin.reclutamiento.dao.mapper.EstadoMapper;
import mx.com.backend.admin.reclutamiento.models.Estado;

@Service
public class EstadoDao implements IEstadoDao {

	private final static Logger log = LoggerFactory.getLogger(EstadoDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Estado> obtenerEstados() throws DaoDataAccesException {
		List<Estado> estados;

		try {
			estados = jdbcTemplate.query("SELECT * FROM admin_360.ESTADOS", new EstadoMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			estados = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL BUSCAR USUARIO POR NONMBRE ::.. [METODO: obtenerEstado] "
					+ e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return estados;
	}

}
