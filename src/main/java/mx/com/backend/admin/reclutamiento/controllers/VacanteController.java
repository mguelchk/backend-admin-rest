package mx.com.backend.admin.reclutamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.response.CustomResponse;
import mx.com.backend.admin.reclutamiento.models.Vacante;
import mx.com.backend.admin.reclutamiento.services.vacante.IVacanteService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class VacanteController {

	@Autowired
	private IVacanteService vacanteService;

	@PostMapping("/vacante")
	public CustomResponse<Vacante> create(@RequestBody Vacante vacante) {

		CustomResponse<Vacante> resp = new CustomResponse<>();

		try {
			Vacante vacResp = vacanteService.crearVacante(vacante);
			resp.success(vacResp, "vacante creado satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@PostMapping("/vacantes")
	public CustomResponse<List<Vacante>> obtenerVacantes(@RequestBody Vacante vacante) {

		CustomResponse<List<Vacante>> resp = new CustomResponse<>();

		try {
			List<Vacante> vacantes = vacanteService.obtenerVacantePorCriterios(vacante);
			resp.success(vacantes, "vacante creado satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@GetMapping("/vacantes/{idVacante}")
	public CustomResponse<Vacante> obtenerVacantes(@PathVariable Long idVacante) {

		CustomResponse<Vacante> resp = new CustomResponse<>();

		try {
			Vacante vacante = vacanteService.obtenerVacantePorId(idVacante);
			resp.success(vacante, "se obtuvo vacante satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@GetMapping("/postulaciones/{idVacante}/{idUsuario}")
	public CustomResponse<Vacante> obtenerPostulaciones(@PathVariable Long idVacante) {

		CustomResponse<Vacante> resp = new CustomResponse<>();

		try {
			Vacante vacante = vacanteService.obtenerVacantePorId(idVacante);
			resp.success(vacante, "Vacante creada satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

}
