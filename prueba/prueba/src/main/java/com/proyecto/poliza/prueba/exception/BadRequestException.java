package com.proyecto.poliza.prueba.exception;

public class BadRequestException extends RuntimeException{
	public BadRequestException(String mensaje) {
		super(mensaje);
	}
}
