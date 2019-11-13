package mx.com.backend.admin.reclutamiento.dao.rol;

import java.util.List;

import mx.com.backend.admin.reclutamiento.dao.entity.Rol;
import mx.com.backend.admin.reclutamiento.dao.entity.Usuario;

public interface IRolDao{
	
	public List<Rol>  obtenerRolesPorUsuario(Usuario usuario);

}
