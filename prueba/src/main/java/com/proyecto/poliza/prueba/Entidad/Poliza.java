package com.proyecto.poliza.prueba.Entidad;

import java.time.LocalDate;

//Capa Modelo
public class Poliza {
	
	private Long idPoliza;
	
	private String tipoSeguro;
	private LocalDate fechaInicio;
	private LocalDate fechaVencimiento;
	private Double montoAsegurado;
	private Object detallesAdicionales;
	
	
	
	public Poliza(Long idPoliza, String tipoSeguro, LocalDate fechaInicio, LocalDate fechaVencimiento,
			Double montoAsegurado, Object detallesAdicionales) {
		super();
		this.idPoliza = idPoliza;
		this.tipoSeguro = tipoSeguro;
		this.fechaInicio = fechaInicio;
		this.fechaVencimiento = fechaVencimiento;
		this.montoAsegurado = montoAsegurado;
		this.detallesAdicionales = detallesAdicionales;
	}
	public Long getIdPoliza() {
		return idPoliza;
	}
	public void setIdPoliza(Long idPoliza) {
		this.idPoliza = idPoliza;
	}
	public String getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Double getMontoAsegurado() {
		return montoAsegurado;
	}
	public void setMontoAsegurado(Double montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}
	public Object getDetallesAdicionales() {
		return detallesAdicionales;
	}
	public void setDetallesAdicionales(Object detallesAdicionales) {
		this.detallesAdicionales = detallesAdicionales;
	}
	
	
	
	
}
