package mx.com.backend.admin.reclutamiento.services.login.impl;

import mx.com.backend.admin.reclutamiento.models.Usuario;

public interface IUsuarioLoginService {

	public Usuario buscarUsuarioPorCorreo(String username);
}