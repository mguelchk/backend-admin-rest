package mx.com.backend.admin.reclutamiento.services;

import mx.com.backend.admin.reclutamiento.dao.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
