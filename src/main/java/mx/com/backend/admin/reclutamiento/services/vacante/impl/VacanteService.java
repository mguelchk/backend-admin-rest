package mx.com.backend.admin.reclutamiento.services.vacante.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.dao.vacante.IVacanteDao;
import mx.com.backend.admin.reclutamiento.models.Vacante;
import mx.com.backend.admin.reclutamiento.services.vacante.IVacanteService;

@Service
public class VacanteService implements IVacanteService {

	private final static Logger log = LoggerFactory.getLogger(VacanteService.class);

	@Autowired
	private IVacanteDao vacanteDao;

	@Override
	public Vacante obtenerVacantePorId(Long idVacante, Long idUsuario) throws BussinesException {
		try {

			return vacanteDao.obtenerVacantePorId(idVacante, idUsuario);

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL OBTENER VACANTE::.. [METODO: obtenerVacantePorId] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

	@Override
	public List<Vacante> obtenerVacantePorCriterios(Vacante vacante) throws BussinesException {
		try {

			StringBuilder query = new StringBuilder();
			List<Object> valores = new ArrayList<Object>();

			this.crearValues(vacante, query, valores);
			Object[] values = new Object[valores.size()];
			values = valores.toArray(values);

			List<Vacante> vacantes = vacanteDao.obtenerVacantesPorCriterios(query.toString(), values);
			return vacantes;

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL OBTENER VACANTE::.. [METODO: obtenerVacantePorCriterios] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

	private void crearValues(Vacante vacante, StringBuilder query, List<Object> valores) throws BussinesException {

		if (vacante == null) {
			throw new BussinesException("el objeto no puede estar vacio");
		}

		if (vacante.getArea().getIdArea() != null) {

			query.append(" AND V.id_area = ? ");

			valores.add(vacante.getArea().getIdArea());

		}

		if (vacante.getEstado().getIdEstado() != null) {

			query.append(" AND V.id_estado = ? ");

			valores.add(vacante.getEstado().getIdEstado());

		}
	}

	@Override
	public Vacante crearVacante(Vacante vacante) throws BussinesException {

		try {

			Vacante vacanteRes = vacanteDao.crearVacante(vacante);
			return vacanteRes;

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL CREAR VACANTE::.. [METODO: crearVacante] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

}
