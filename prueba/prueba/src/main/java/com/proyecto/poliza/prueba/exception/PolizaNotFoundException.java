package com.proyecto.poliza.prueba.exception;

public class PolizaNotFoundException extends RuntimeException{
	public PolizaNotFoundException(String mensaje) {
		super(mensaje);
	}
}
