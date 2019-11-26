package mx.com.backend.admin.reclutamiento.services.area.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.dao.area.IAreaDao;
import mx.com.backend.admin.reclutamiento.models.Area;
import mx.com.backend.admin.reclutamiento.services.area.IAreaService;
import mx.com.backend.admin.reclutamiento.services.estado.impl.EstadoService;

@Service
public class AreaService implements IAreaService {

	private final static Logger log = LoggerFactory.getLogger(EstadoService.class);

	@Autowired
	private IAreaDao areaDao;

	@Override
	public List<Area> obtenerAreasPorNombre(String nombre) throws BussinesException {
		try {

			return areaDao.obtenerAreasPorNombre(nombre);

		} catch (DaoDataAccesException e) {

			log.error("..:: ERROR AL OBTENER VACANTE::.. [METODO: obtenerAreasPorNombre] " + e.getMessage(), e);
			throw new BussinesException(e);

		}
	}

}
