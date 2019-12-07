package mx.com.backend.admin.reclutamiento.dao.vacante;

import java.util.List;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.models.Vacante;

public interface IVacanteDao {

	public Vacante obtenerVacantePorId(Long idVacante, Long idUsuario) throws DaoDataAccesException;

	public List<Vacante> obtenerVacantesPorCriterios(String qryRestantes, Object[] values) throws DaoDataAccesException;

	public Vacante crearVacante(Vacante vacante) throws DaoDataAccesException;

}
