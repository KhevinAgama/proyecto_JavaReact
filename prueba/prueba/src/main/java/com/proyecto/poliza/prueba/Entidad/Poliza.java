package com.proyecto.poliza.prueba.Entidad;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Poliza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPoliza;
	
	private String tipoSeguro;
	private LocalDate fechaInicio;
	private LocalDate fechaVencimiento;
	private Double montoAsegurado;
	private String detallesAdicionales;
	
	public int getIdPoliza() {
		return idPoliza;
	}
	public void setIdPoliza(int idPoliza) {
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
	public String getDetallesAdicionales() {
		return detallesAdicionales;
	}
	public void setDetallesAdicionales(String detallesAdicionales) {
		this.detallesAdicionales = detallesAdicionales;
	}
	
	
	
	
}
