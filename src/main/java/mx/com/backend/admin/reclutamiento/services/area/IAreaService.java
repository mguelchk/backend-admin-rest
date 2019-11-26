package mx.com.backend.admin.reclutamiento.services.area;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.models.Area;

public interface IAreaService {

	public List<Area> obtenerAreasPorNombre(String nombre) throws BussinesException;
}
