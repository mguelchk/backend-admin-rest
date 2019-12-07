package mx.com.backend.admin.reclutamiento.services.vacante;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.models.Vacante;

public interface IVacanteService {

	public Vacante obtenerVacantePorId(Long idVacante, Long idUsuario) throws BussinesException;
	
	public List<Vacante> obtenerVacantePorCriterios(Vacante vacante) throws BussinesException;
	
	public Vacante crearVacante(Vacante vacante) throws BussinesException;

}
