package mx.com.backend.admin.reclutamiento.services.estado.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.dao.estado.IEstadoDao;
import mx.com.backend.admin.reclutamiento.models.Estado;
import mx.com.backend.admin.reclutamiento.services.estado.IEstadoService;

@Service
public class EstadoService implements IEstadoService {

	private final static Logger log = LoggerFactory.getLogger(EstadoService.class);

	@Autowired
	private IEstadoDao estadoDao;

	@Override
	public List<Estado> obtenerEstado() throws BussinesException {
		try {

			return estadoDao.obtenerEstados();

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL OBTENER VACANTE::.. [METODO: obtenerEstado] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

}
