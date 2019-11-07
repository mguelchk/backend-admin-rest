package mx.com.admin.reclutamiento.services;

import mx.com.admin.reclutamiento.dao.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
