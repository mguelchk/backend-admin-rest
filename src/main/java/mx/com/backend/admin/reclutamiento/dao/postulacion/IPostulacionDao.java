package mx.com.backend.admin.reclutamiento.dao.postulacion;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.models.Postulacion;

public interface IPostulacionDao {

	public Postulacion crearPostulacionPorUsuario(Postulacion postulacion) throws DaoDataAccesException;

	public List<Postulacion> obtenerPostulacionesPorCriterios(String string, Object[] values) throws DaoDataAccesException;;

}
