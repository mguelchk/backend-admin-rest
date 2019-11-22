package mx.com.backend.admin.reclutamiento.dao.usuario;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.models.Usuario;

public interface IUsuarioDao {

	public Usuario buscarUsuarioPorCorreo(String username) throws DaoDataAccesException;

	public Usuario buscarUsuarioPorId(int idUser) throws DaoDataAccesException;;

	public Usuario crearUsuario(Usuario usuario) throws DaoDataAccesException;

	public Usuario actualizarRecoverUsuario(Usuario usuario) throws DaoDataAccesException;

	public Usuario actualizarPassword(Usuario user)throws DaoDataAccesException;

}
