package mx.com.backend.admin.reclutamiento.dao.vacante.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.core.exception.SpErrorException;
import mx.com.backend.admin.reclutamiento.dao.mapper.VacanteMapper;
import mx.com.backend.admin.reclutamiento.dao.sp.VacanteSP;
import mx.com.backend.admin.reclutamiento.dao.vacante.IVacanteDao;
import mx.com.backend.admin.reclutamiento.models.Vacante;

@Service
public class VacanteDao implements IVacanteDao {

	private final static Logger log = LoggerFactory.getLogger(VacanteDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Vacante obtenerVacantePorId(Long idVacante) throws DaoDataAccesException {

		Vacante vac;

		try {
			vac = jdbcTemplate.queryForObject("SELECT * FROM admin_360.VACANTES WHEREN id_vacante = ? AND activo = 1",
					new Object[] { idVacante }, new VacanteMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			vac = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL BUSCAR USUARIO POR NONMBRE ::.. [METODO: buscarUsuarioPorCorreo] "
					+ e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return vac;
	}

	@Override
	public Vacante crearVacante(Vacante vacante) throws DaoDataAccesException {
		try {

			VacanteSP sp = new VacanteSP(jdbcTemplate, "crearVacante");

			Vacante vac = obtenerVacantePorId(sp.crearVacante(vacante).longValue());

			return vac;

		} catch (SpErrorException e) {

			log.error("..:: ERROR CAPA DAO AL CREAR VACANTE::.. [METODO: crearVacante] " + e.getMessage(), e);

			throw new DaoDataAccesException(e);
		}
	}

	@Override
	public List<Vacante> obtenerVacantesPorCriterios(String qryRestantes, Object[] values)
			throws DaoDataAccesException {
		List<Vacante> vacantes;
		try {
			vacantes = jdbcTemplate.query("SELECT * FROM VACANTES WHERE publicada = 1 AND activo = 1 " + qryRestantes,
					values, new VacanteMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			vacantes = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL BUSCAR USUARIO POR NONMBRE ::.. [METODO: buscarUsuarioPorCorreo] "
					+ e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return vacantes;
	}

}
