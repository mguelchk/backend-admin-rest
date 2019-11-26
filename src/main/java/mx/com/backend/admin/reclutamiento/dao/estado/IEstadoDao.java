package mx.com.backend.admin.reclutamiento.dao.estado;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.models.Estado;

public interface IEstadoDao {

	public List<Estado> obtenerEstados() throws DaoDataAccesException;

}
