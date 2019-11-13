package mx.com.backend.admin.reclutamiento.dao.menu;

import java.util.List;

import mx.com.backend.admin.reclutamiento.dao.entity.Menu;
import mx.com.backend.admin.reclutamiento.dao.entity.Rol;

public interface IMenuDao{
	
	public List<Menu>  obtenerMenusPorRol(Rol rol);

}
