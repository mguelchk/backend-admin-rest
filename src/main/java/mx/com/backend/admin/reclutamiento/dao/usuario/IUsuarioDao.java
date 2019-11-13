package mx.com.backend.admin.reclutamiento.dao.usuario;

import mx.com.backend.admin.reclutamiento.dao.entity.Usuario;

public interface IUsuarioDao {

	public Usuario findByUsername(String username);

}
