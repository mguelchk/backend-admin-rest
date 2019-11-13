package mx.com.backend.admin.reclutamiento.dao.entity;

import java.io.Serializable;
import java.util.Date;


public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	private String nombre;
	private Double precio;

	private Date createAt;

	public void prePersist() {
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	
}
