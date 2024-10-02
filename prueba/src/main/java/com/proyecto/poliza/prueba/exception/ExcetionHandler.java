package com.proyecto.poliza.prueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcetionHandler {
	@ExceptionHandler(PolizaNotFoundException.class)
	public ResponseEntity<String> handlePolizaNotFound(PolizaNotFoundException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
