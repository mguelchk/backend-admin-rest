package mx.com.backend.admin.reclutamiento.dao.usuario;

import mx.com.backend.admin.reclutamiento.core.exception.DataAccesException;
import mx.com.backend.admin.reclutamiento.models.Usuario;

public interface IUsuarioDao {

	public Usuario buscarUsuarioPorCorreo(String username);

	public Usuario buscarUsuarioPorId(int idUser);
	
	public Usuario crearUsuario(Usuario usuario) throws DataAccesException;

}
