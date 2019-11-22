package mx.com.backend.admin.reclutamiento.models;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;

	private String nombreUsuario;

	private String password;

	private String passwordTmp;

	private String nombre;

	private String email;

	private String telefono;

	private Boolean activo;

	private Boolean recover;

	private List<Rol> roles;

	private List<Menu> menus;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordTmp() {
		return passwordTmp;
	}

	public void setPasswordTmp(String passwordTmp) {
		this.passwordTmp = passwordTmp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getRecover() {
		return recover;
	}

	public void setRecover(Boolean recover) {
		this.recover = recover;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
