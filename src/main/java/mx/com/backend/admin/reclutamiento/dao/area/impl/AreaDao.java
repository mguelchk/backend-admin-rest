package mx.com.backend.admin.reclutamiento.dao.area.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.core.exception.SpErrorException;
import mx.com.backend.admin.reclutamiento.dao.area.IAreaDao;
import mx.com.backend.admin.reclutamiento.dao.estado.impl.EstadoDao;
import mx.com.backend.admin.reclutamiento.dao.mapper.AreaMapper;
import mx.com.backend.admin.reclutamiento.dao.sp.AreaSP;
import mx.com.backend.admin.reclutamiento.models.Area;

@Service
public class AreaDao implements IAreaDao {

	private final static Logger log = LoggerFactory.getLogger(EstadoDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Area obtenerAreaPorId(int idArea) throws DaoDataAccesException {
		Area area;

		try {
			area = jdbcTemplate.queryForObject("SELECT * FROM admin_360.AREAS WHERE  id_area =  ?",
					new Object[] { idArea}, new AreaMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			area = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO ::.. [METODO: obtenerAreaPorId] "
					+ e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return area;
	}
	

	@Override
	public List<Area> obtenerAreasPorNombre(String nombre) throws DaoDataAccesException {
		List<Area> areas;

		try {
			areas = jdbcTemplate.query("SELECT * FROM admin_360.AREAS WHERE  nombre like ?",
					new Object[] { "%" + nombre + "%" }, new AreaMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			areas = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL BUSCAR USUARIO POR NONMBRE ::.. [METODO: obtenerAreasPorNombre] "
					+ e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return areas;
	}

	@Override
	public List<Area> obtenerAreas() throws DaoDataAccesException {
		List<Area> areas;

		try {
			areas = jdbcTemplate.query("SELECT * FROM admin_360.AREAS WHERE  activo = 1 order by id_area desc ", new AreaMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			areas = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL OBTENER AREAS ::.. [METODO: obtenerAreas] " + e.getMessage(),
					e);
			throw new DaoDataAccesException(e);
		}
		return areas;
	}

	@Override
	public Area crearActualizarArea(Area area) throws DaoDataAccesException {
		try {
			AreaSP sp = new AreaSP(jdbcTemplate, "crearActualizarArea");
			return obtenerAreaPorId(sp.crearActualizarArea(area));

		} catch (SpErrorException e) {
			log.error("..:: ERROR CAPA DAO AL CREAR USUARIO::.. [METODO: crearActuzalizarArea] " + e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
	}


	@Override
	public Boolean deleteArea(Integer idArea) throws DaoDataAccesException {

		try {
			int exito = 0; 
			exito = jdbcTemplate.update(
	                "DELETE FROM admin_360.AREAS WHERE  id_area = ? ",
	                idArea);
			return exito == 1;
		}  catch (Exception e) {
			log.error("..:: ERROR CAPA DAO ::.. [METODO: deleteArea] "
					+ e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
	}

	

	

}
