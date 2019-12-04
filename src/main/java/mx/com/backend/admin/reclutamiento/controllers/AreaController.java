package mx.com.backend.admin.reclutamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.backend.admin.reclutamiento.core.exception.BussinesException;
import mx.com.backend.admin.reclutamiento.core.response.CustomResponse;
import mx.com.backend.admin.reclutamiento.models.Area;
import mx.com.backend.admin.reclutamiento.services.area.IAreaService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class AreaController {

	@Autowired
	private IAreaService areaService;

	@GetMapping("/areas/{nombre}")
	public CustomResponse<List<Area>> obtenerEstados(@PathVariable String nombre) {

		CustomResponse<List<Area>> resp = new CustomResponse<>();

		try {
			List<Area> vacantes = areaService.obtenerAreasPorNombre(nombre);
			resp.success(vacantes, "se consulto las areas satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}
}
