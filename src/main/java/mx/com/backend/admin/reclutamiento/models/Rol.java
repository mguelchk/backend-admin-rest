package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;
import java.util.List;

public class Rol implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idRol;
	
	private String nombre;

	private List<Menu> menus;
	
	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	

	
}
