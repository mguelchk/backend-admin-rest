package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idCliente;
	private String nombre;
	private String descripcion;
	private Estado estado;
	private String direccion;
	private Integer activo;

	public Cliente() {

	}

	public Cliente(Long idCliente, String nombre, String descripcion, Estado estado, String direccion, Integer activo) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.direccion = direccion;
		this.activo = activo;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}
