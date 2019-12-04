package mx.com.backend.admin.reclutamiento.services.postulacion;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.models.Postulacion;

public interface IPostulacionService {

	public Postulacion crearPostulacioneDeUsuario(Postulacion postulacion) throws BussinesException;

	public List<Postulacion> obtenerPostulacionesPorCriterios(String condiciones) throws BussinesException;;

}
