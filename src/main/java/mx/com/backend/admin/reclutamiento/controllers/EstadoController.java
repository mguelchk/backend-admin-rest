package mx.com.backend.admin.reclutamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.response.CustomResponse;
import mx.com.backend.admin.reclutamiento.models.Estado;
import mx.com.backend.admin.reclutamiento.services.estado.IEstadoService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class EstadoController {

	@Autowired
	private IEstadoService estadoService;

	@GetMapping("/estados")
	public CustomResponse<List<Estado>> obtenerEstados() {

		CustomResponse<List<Estado>> resp = new CustomResponse<>();

		try {
			List<Estado> vacantes = estadoService.obtenerEstado();
			resp.success(vacantes, "se consulto los estados satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

}
