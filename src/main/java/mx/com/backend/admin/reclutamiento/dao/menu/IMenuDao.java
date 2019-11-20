package mx.com.backend.admin.reclutamiento.dao.menu;

import java.util.List;

import mx.com.backend.admin.reclutamiento.models.Menu;
import mx.com.backend.admin.reclutamiento.models.Rol;

public interface IMenuDao{
	
	public List<Menu>  obtenerMenusPorRol(Rol rol);

}
