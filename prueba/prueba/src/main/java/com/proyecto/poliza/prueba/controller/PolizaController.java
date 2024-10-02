package com.proyecto.poliza.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polizas")
public class PolizaController {
	
	@GetMapping("/hello")
	public String hellloWorld() {
		return "Hello World Peru";
	}
}
