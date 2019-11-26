package mx.com.backend.admin.reclutamiento.services.estado;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.models.Estado;

public interface IEstadoService {

	public List<Estado> obtenerEstado() throws BussinesException;
}
