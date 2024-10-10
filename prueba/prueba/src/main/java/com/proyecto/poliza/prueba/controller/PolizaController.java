package com.proyecto.poliza.prueba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.proyecto.poliza.prueba.entidad.Poliza;
import com.proyecto.poliza.prueba.servicio.PolizaService;

@RestController
@RequestMapping("/polizas")
public class PolizaController {
	
	//@GetMapping("/hello")
	//public String hellloWorld() {
	//	return "Hello Peru";
	//}
	
	@Autowired
	private PolizaService polizaService;
	
	public PolizaController(PolizaService polizaService) {
		this.polizaService = polizaService;
	}
	
	@PostMapping("/crearPoliza")
	public Poliza crearPoliza(@RequestBody Poliza poliza){
		return polizaService.crearPoliza(poliza);
		
	}
	
	@GetMapping("/listarPoliza")
	public List<Poliza> consultarPolizas(){
		return polizaService.consultarPolizas();
	}
	
	@GetMapping("/obtenerPolizaPorId/{id}")
	public Poliza obtenerPolizaPorId(@PathVariable Long id) {
		return polizaService.obtenerPolizaPorId(id);
	} 
	
	@PutMapping("/actualizarPoliza/{id}")
	public Poliza actualizarPoliza(@PathVariable Long id, 
													@RequestBody Poliza polizaActualizada){
		return polizaService.actualizarPoliza(id, polizaActualizada);
		
	} 
	
	@DeleteMapping("/eliminarPoliza/{id}")
	public void eliminarPoliza(@PathVariable Long id){
		polizaService.eliminarPoliza(id);
	}
}
