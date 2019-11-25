package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;
import java.util.Date;

public class Vacante implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idVacante;
	private String nombre;
	private String sueldo;
	private String descripcionBreve;
	private Area area;
	private Estado estado;
	private Cliente cliente;
	private String descripcion;
	private String requisitos;
	private Integer numVacante;
	private Date FechaContratacion;
	private String experiencia;
	private String nivelEstudios;
	private boolean disponibilidadViajar;
	private String idioma;
	private String rangoEdad;
	private String tipoLicencia;
	private String discapacidad;
	private boolean publicada;
	private boolean activo;

	public Long getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(Long idVacante) {
		this.idVacante = idVacante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSueldo() {
		return sueldo;
	}

	public void setSueldo(String sueldo) {
		this.sueldo = sueldo;
	}

	public String getDescripcionBreve() {
		return descripcionBreve;
	}

	public void setDescripcionBreve(String descripcionBreve) {
		this.descripcionBreve = descripcionBreve;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public Integer getNumVacante() {
		return numVacante;
	}

	public void setNumVacante(Integer numVacante) {
		this.numVacante = numVacante;
	}

	public Date getFechaContratacion() {
		return FechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		FechaContratacion = fechaContratacion;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(String nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public boolean isDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	public void setDisponibilidadViajar(boolean disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getRangoEdad() {
		return rangoEdad;
	}

	public void setRangoEdad(String rangoEdad) {
		this.rangoEdad = rangoEdad;
	}

	public String getTipoLicencia() {
		return tipoLicencia;
	}

	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public boolean isPublicada() {
		return publicada;
	}

	public void setPublicada(boolean publicada) {
		this.publicada = publicada;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
