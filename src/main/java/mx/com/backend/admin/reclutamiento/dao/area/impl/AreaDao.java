package mx.com.backend.admin.reclutamiento.dao.area.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.dao.area.IAreaDao;
import mx.com.backend.admin.reclutamiento.dao.estado.impl.EstadoDao;
import mx.com.backend.admin.reclutamiento.dao.mapper.AreaMapper;
import mx.com.backend.admin.reclutamiento.models.Area;

@Service
public class AreaDao implements IAreaDao {

	private final static Logger log = LoggerFactory.getLogger(EstadoDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Area> obtenerAreasPorNombre(String nombre) throws DaoDataAccesException {
		List<Area> areas;

		try {
			areas = jdbcTemplate.query("SELECT * FROM admin_360.AREAS WHERE nombre like ?", new Object[] {  "%" + nombre + "%" },
					new AreaMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			areas = null;

		} catch (Exception e) {
			log.error(
					"..:: ERROR CAPA DAO AL BUSCAR USUARIO POR NONMBRE ::.. [METODO: obtenerAreasPorNombre] " + e.getMessage(),
					e);
			throw new DaoDataAccesException(e);
		}
		return areas;
	}

}
