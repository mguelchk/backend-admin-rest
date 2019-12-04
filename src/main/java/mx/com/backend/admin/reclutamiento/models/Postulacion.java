package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;

public class Postulacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private Vacante vacante;

	private Integer totalCandidatos;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vacante getVacante() {
		return vacante;
	}

	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}

	public Integer getTotalCandidatos() {
		return totalCandidatos;
	}

	public void setTotalCandidatos(Integer totalCandidatos) {
		this.totalCandidatos = totalCandidatos;
	}

}
