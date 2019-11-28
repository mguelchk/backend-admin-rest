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
			vacantes = jdbcTemplate.query("SELECT\n" + 
					"V.*,\n" + 
					"C.id_cliente, \n" + 
					"C.nombre as nombre_cliente,\n" + 
					"E.id_estado,\n" + 
					"E.nombre as nombre_estado,\n" + 
					"A.id_area,\n" + 
					"A.nombre as nombre_area\n" + 
					" FROM VACANTES V\n" + 
					"INNER JOIN CLIENTES C ON V.id_cliente = C.id_cliente\n" + 
					"INNER JOIN ESTADOS E ON V.id_estado = E.id_estado\n" + 
					"INNER JOIN AREAS A ON V.id_area = A.id_area\n" + 
					"WHERE V.publicada = 1 AND V.activo = 1 " + qryRestantes,
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
