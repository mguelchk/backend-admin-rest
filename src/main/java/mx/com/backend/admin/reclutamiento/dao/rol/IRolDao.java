package mx.com.backend.admin.reclutamiento.dao.rol;

import java.util.List;

import mx.com.backend.admin.reclutamiento.models.Rol;
import mx.com.backend.admin.reclutamiento.models.Usuario;

public interface IRolDao{
	
	public List<Rol>  obtenerRolesPorUsuario(Usuario usuario);

}
