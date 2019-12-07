package mx.com.backend.admin.reclutamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public CustomResponse<List<Area>> obtenerAreasPorNombre(@PathVariable String nombre) {

		CustomResponse<List<Area>> resp = new CustomResponse<>();

		try {
			List<Area> vacantes = areaService.obtenerAreasPorNombre(nombre);
			resp.success(vacantes, "se consulto las areas satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@GetMapping("/areas-todos")
	public CustomResponse<List<Area>> obtenerAreas() {

		CustomResponse<List<Area>> resp = new CustomResponse<>();

		try {
			List<Area> area = areaService.obtenerAreas();
			resp.success(area, "se consulto las areas satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@PostMapping("/areas")
	public CustomResponse<Area> crearActualizarArea(@RequestBody Area area) {

		CustomResponse<Area> resp = new CustomResponse<>();

		try {
			Area areaResp = areaService.crearActualizarArea(area);
			resp.success(areaResp, "se realizo la transacción satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}

	@DeleteMapping("/areas/{idArea}")
	public CustomResponse<Boolean> eliminarArea(@PathVariable Integer idArea) {

		CustomResponse<Boolean> resp = new CustomResponse<>();

		try {
			Boolean eliminado = areaService.deleteArea(idArea);
			resp.success(eliminado, "se realizo la transacción satisfactoriamente");
		} catch (BussinesException e) {
			resp.error(e.getMessage(), e);
		}
		return resp;
	}
}
