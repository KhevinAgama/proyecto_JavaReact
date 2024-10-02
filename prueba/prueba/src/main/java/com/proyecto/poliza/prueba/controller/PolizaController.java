package com.proyecto.poliza.prueba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.poliza.prueba.Entidad.Poliza;
import com.proyecto.poliza.prueba.servicio.PolizaService;

@RestController
@RequestMapping("/polizas")
public class PolizaController {
	
	//@GetMapping("/hello")
	//public String hellloWorld() {
	//	return "Hello Peru";
	//}
	
	private final PolizaService polizaService;
	
	public PolizaController(PolizaService polizaService) {
		this.polizaService = polizaService;
	}
	
	@PostMapping("/crearPoliza")
	public ResponseEntity<?> crearPoliza(@RequestBody Poliza poliza){
		Poliza nuevaPoliza = polizaService.crearPoliza(poliza);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("mensaje", "Poliza creado con Éxito.");
		responseMap.put("usuario", nuevaPoliza);
		return new ResponseEntity<>(responseMap,HttpStatus.CREATED);
	}
	
	@GetMapping("/listarPoliza")
	public ResponseEntity<List<Poliza>> consultarPolizas(){
		List<Poliza> polizas = polizaService.consultarPolizas();
		return new ResponseEntity<>(polizas,HttpStatus.OK);
	}
	
	@PutMapping("/actualizarPoliza/{id}")
	public ResponseEntity<Poliza> actualizarPoliza(@PathVariable Long id, 
													@RequestBody Poliza polizaActualizada){
		Poliza poliza = polizaService.actualizarPoliza(id, polizaActualizada);
		return new ResponseEntity<>(poliza,HttpStatus.OK);
	} 
	
	@DeleteMapping("/eliminarPoliza/{id}")
	public ResponseEntity<Void> eliminarPoliza(@PathVariable Long id){
		polizaService.eliminarPoliza(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
