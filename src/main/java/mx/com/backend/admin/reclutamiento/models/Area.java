package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;

public class Area implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idArea;
	private String nombre;
	private String descripcion;
	private Integer activo;

	public Area() {
		//contructor por default
	}

	public Area(Long idArea, String nombre, String descripcion, Integer activo) {
		this.idArea = idArea;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}
