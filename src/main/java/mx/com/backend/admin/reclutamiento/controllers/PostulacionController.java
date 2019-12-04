package mx.com.backend.admin.reclutamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.response.CustomResponse;
import mx.com.backend.admin.reclutamiento.models.Postulacion;
import mx.com.backend.admin.reclutamiento.services.postulacion.IPostulacionService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class PostulacionController {

	@Autowired
	private IPostulacionService postulacionService;

	@PostMapping("/postulaciones")
	public CustomResponse<Postulacion> crearPostulacioneDeUsuario(@RequestBody Postulacion postulacion) {

		CustomResponse<Postulacion> resp = new CustomResponse<>();

		try {
			Postulacion postResp = postulacionService.crearPostulacioneDeUsuario(postulacion);
			resp.success(postResp, "se postulo a vacante satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@GetMapping("/postulaciones")
	public CustomResponse<List<Postulacion>> obtenerPostulaciones(@RequestParam(required = false) String condiciones) {

		CustomResponse<List<Postulacion>> resp = new CustomResponse<>();

		try {
			List<Postulacion> postulaciones = postulacionService.obtenerPostulacionesPorCriterios(condiciones);
			resp.success(postulaciones, "se obtuvieron las postulaciones satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

}
