package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;

public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEstado;
	private String nombre;
	private String codigo;
	private Integer activo;

	public Estado() {
		
		// contructor estado
		
	}

	public Estado(Long idEstado, String nombre, String codigo, Integer activo) {
		
		this.idEstado = idEstado;
		this.nombre = nombre;
		this.codigo = codigo;
		this.activo = activo;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}
