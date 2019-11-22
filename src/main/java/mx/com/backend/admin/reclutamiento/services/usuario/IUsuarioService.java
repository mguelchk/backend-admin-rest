package mx.com.backend.admin.reclutamiento.services.usuario;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.models.Usuario;

public interface IUsuarioService {

	public Usuario crearUsuario(Usuario usuario) throws BussinesException;

	public Usuario reestablecerContrasenia(Usuario usuario) throws BussinesException;

	public Usuario actualizarPassword(Usuario uuario) throws BussinesException;
}
