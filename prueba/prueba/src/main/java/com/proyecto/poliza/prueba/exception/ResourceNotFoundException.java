package com.proyecto.poliza.prueba.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String mensaje) {
		super(mensaje);
	}
}
