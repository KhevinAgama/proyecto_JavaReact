package com.proyecto.poliza.prueba.exception;

import org.springframework.http.HttpStatus;

//clase para encapsular cuando ocurra un excepcion
public class ErrorResponse {
	private HttpStatus status;
	private String message;
	private String details;
	
	public ErrorResponse(HttpStatus status, String message, String details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
}
