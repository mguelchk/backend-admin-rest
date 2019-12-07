package mx.com.backend.admin.reclutamiento.dao.area;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.models.Area;

public interface IAreaDao {

	public Area obtenerAreaPorId(int idArea) throws DaoDataAccesException;

	public List<Area> obtenerAreasPorNombre(String nombre) throws DaoDataAccesException;

	public List<Area> obtenerAreas() throws DaoDataAccesException;

	public Area crearActualizarArea(Area area) throws DaoDataAccesException;

	public Boolean deleteArea(Integer idArea) throws DaoDataAccesException;
}
