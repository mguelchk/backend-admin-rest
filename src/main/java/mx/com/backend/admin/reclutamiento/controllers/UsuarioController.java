package mx.com.backend.admin.reclutamiento.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.response.CustomResponse;
import mx.com.backend.admin.reclutamiento.models.Usuario;
import mx.com.backend.admin.reclutamiento.services.usuario.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping("/usuario")
	public CustomResponse<Usuario> create(@RequestBody Usuario uuario) {

		CustomResponse<Usuario> resp = new CustomResponse<>();

		try {
			Usuario iuser = usuarioService.crearUsuario(uuario);
			resp.success(iuser, "Se creo el registro satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@PostMapping("/recuperar")
	public CustomResponse<Usuario> recuperarPassword(@RequestBody Usuario uuario) {

		CustomResponse<Usuario> resp = new CustomResponse<>();

		try {
			Usuario iuser = usuarioService.reestablecerContrasenia(uuario);
			resp.success(iuser, "Se envio la informacion a su  correo");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@PostMapping("/actualizar")
	public CustomResponse<Usuario> actualizarPassword(@RequestBody Usuario uuario) {

		CustomResponse<Usuario> resp = new CustomResponse<>();

		try {
			Usuario iuser = usuarioService.actualizarPassword(uuario);
			resp.success(iuser, "Se actualizo su contraseña satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

}
