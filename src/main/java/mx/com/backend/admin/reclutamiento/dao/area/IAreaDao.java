package mx.com.backend.admin.reclutamiento.dao.area;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.models.Area;

public interface IAreaDao {

	public List<Area> obtenerAreasPorNombre(String nombre) throws DaoDataAccesException;

}
