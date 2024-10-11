package com.proyecto.poliza.prueba.dto;

import java.time.LocalDate;

import com.proyecto.poliza.prueba.entidad.Poliza;

public class PolizaDTO {

	private Long id_poliza;
	private String tipo_seguro;
	private LocalDate fecha_inicio;
	private LocalDate fecha_vencimiento;
	private Double monto_asegurado;
	private String detalles_adicionales;
	private Long id_usuario;
	
	
	
	public PolizaDTO(Poliza poliza) {
		super();
		this.id_poliza = poliza.getId_poliza();
		this.tipo_seguro = poliza.getTipo_seguro();
		this.fecha_inicio = poliza.getFecha_inicio();
		this.fecha_vencimiento = poliza.getFecha_vencimiento();
		this.monto_asegurado = poliza.getMonto_asegurado();
		this.detalles_adicionales = poliza.getDetalles_adicionales();
		this.id_usuario = poliza.getUsuario().getId_usuario();
	}

	public PolizaDTO() {
	}

	public Long getId_poliza() {
		return id_poliza;
	}

	public void setId_poliza(Long id_poliza) {
		this.id_poliza = id_poliza;
	}

	public String getTipo_seguro() {
		return tipo_seguro;
	}

	public void setTipo_seguro(String tipo_seguro) {
		this.tipo_seguro = tipo_seguro;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public Double getMonto_asegurado() {
		return monto_asegurado;
	}

	public void setMonto_asegurado(Double monto_asegurado) {
		this.monto_asegurado = monto_asegurado;
	}

	public String getDetalles_adicionales() {
		return detalles_adicionales;
	}

	public void setDetalles_adicionales(String detalles_adicionales) {
		this.detalles_adicionales = detalles_adicionales;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

		
}