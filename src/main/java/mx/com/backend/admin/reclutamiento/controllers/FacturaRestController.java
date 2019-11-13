package mx.com.backend.admin.reclutamiento.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class FacturaRestController {

//	@Autowired
//	private IClienteService clienteService;
//
//	@Secured({"ROLE_ADMIN", "ROLE_USER"})
//	@GetMapping("/facturas/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public Factura show(@PathVariable Long id) {
//		return clienteService.findFacturaById(id);
//	}
//	
//	@Secured({"ROLE_ADMIN"})
//	@DeleteMapping("/facturas/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable Long id) {
//		clienteService.deleteFacturaById(id);
//	}
//	
//	@Secured({"ROLE_ADMIN"})
//	@GetMapping("/facturas/filtrar-productos/{term}")
//	@ResponseStatus(HttpStatus.OK)
//	public List<Producto> filtrarProductos(@PathVariable String term){
//		return clienteService.findProductoByNombre(term);
//	}
//	
//	@Secured({"ROLE_ADMIN"})
//	@PostMapping("/facturas")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Factura crear(@RequestBody Factura factura) {
//		return clienteService.saveFactura(factura);
//	}

}
