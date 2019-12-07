package mx.com.backend.admin.reclutamiento.services.postulacion.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.dao.postulacion.IPostulacionDao;
import mx.com.backend.admin.reclutamiento.dao.vacante.IVacanteDao;
import mx.com.backend.admin.reclutamiento.models.Postulacion;
import mx.com.backend.admin.reclutamiento.models.Vacante;
import mx.com.backend.admin.reclutamiento.services.postulacion.IPostulacionService;
import mx.com.backend.admin.reclutamiento.services.vacante.IVacanteService;
import mx.com.backend.admin.reclutamiento.services.vacante.impl.VacanteService;

@Service
public class PostulacionService implements IPostulacionService {

	private final static Logger log = LoggerFactory.getLogger(VacanteService.class);

	@Autowired
	private IPostulacionDao postulacionDao;
	@Autowired
	private IVacanteDao vacanteDao;

	@Override
	public Postulacion crearPostulacioneDeUsuario(Postulacion postulacion) throws BussinesException {
		try {
			postulacionDao.crearPostulacionPorUsuario(postulacion);
			Postulacion pos = new Postulacion();
			Vacante vac = vacanteDao.obtenerVacantePorId(postulacion.getVacante().getIdVacante(),
					postulacion.getUsuario().getIdUsuario());
			pos.setVacante(vac);
			return pos;

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL CREAR POSTULACION::.. [METODO: crearPostulacioneDeUsuario] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

	@Override
	public List<Postulacion> obtenerPostulacionesPorCriterios(String condiciones) throws BussinesException {
		try {

			StringBuilder query = new StringBuilder();
			List<Object> valores = new ArrayList<Object>();
			Object[] values = new Object[0];
			if (condiciones != null && !condiciones.isEmpty()) {
				this.crearValues(condiciones, query, valores);
				values = new Object[valores.size()];
				values = valores.toArray(values);
			}

			List<Postulacion> postulaciones = postulacionDao.obtenerPostulacionesPorCriterios(query.toString(), values);
			return postulaciones;

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL OBTENER VACANTE::.. [METODO: obtenerVacantePorCriterios] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

	private void crearValues(String condiciones, StringBuilder query, List<Object> valores) {

		String valore[] = condiciones.split("##");
		if (valore[0] != null) {
			query.append("");
			valores.add(valore[0]);
		}
		if (valore[1] != null) {
			query.append("");
			valores.add(valore[1]);
		}
		if (valore[2] != null) {
			query.append("");
			valores.add(valore[2]);
		}
	}

}
