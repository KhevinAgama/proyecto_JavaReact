package com.proyecto.poliza.prueba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.poliza.prueba.dto.PolizaDTO;
import com.proyecto.poliza.prueba.entidad.Poliza;
import com.proyecto.poliza.prueba.servicio.PolizaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/polizas")
public class PolizaController {
	
	@Autowired
	private PolizaService polizaService;
	
	public PolizaController(PolizaService polizaService) {
		this.polizaService = polizaService;
	}
	
	@PostMapping("/crearPoliza")
	public ResponseEntity<PolizaDTO> crearPoliza(@Valid @RequestBody PolizaDTO polizaDTO){
		PolizaDTO nuevaPolizaDTO = polizaService.crearPoliza(polizaDTO);
		return new ResponseEntity<>(nuevaPolizaDTO, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/listarPoliza")
	public List<PolizaDTO> consultarPolizas(){
		return polizaService.consultarPolizas();
	}
	
	@GetMapping("/obtenerPolizaPorId/{id}")
	public ResponseEntity<PolizaDTO> obtenerPolizaPorId(@PathVariable Long id) {
		PolizaDTO polizaDTO = polizaService.obtenerPolizaPorId(id);
		return ResponseEntity.ok(polizaDTO);
	} 
	
	@PutMapping("/actualizarPoliza/{id}")
	public ResponseEntity<PolizaDTO>  actualizarPoliza(@PathVariable Long id, 
													@Valid @RequestBody PolizaDTO polizaDTOActualizada){
		 PolizaDTO polizaDTO = polizaService.actualizarPoliza(id, polizaDTOActualizada);
		 return ResponseEntity.ok(polizaDTO);
	} 
	
	@DeleteMapping("/eliminarPoliza/{id}")
	public ResponseEntity<Void> eliminarPoliza(@PathVariable Long id){
		polizaService.eliminarPoliza(id);
		return ResponseEntity.noContent().build();
	}
}
