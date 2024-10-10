package com.proyecto.poliza.prueba.entidad;

import java.time.LocalDate;
import jakarta.persistence.*;

//Capa Modelo

@Entity
@Table(name = "polizas")
public class Poliza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_poliza")
	private Long id_poliza;
	
	@Column(name="tipo_seguro", nullable = false)
	private String tipo_seguro;
	
	@Column(name="fecha_inicio", nullable = false)
	private LocalDate fecha_inicio;
	
	@Column(name="fecha_vencimiento", nullable = false)
	private LocalDate fecha_vencimiento;
	
	@Column(name="monto_asegurado", nullable = false)
	private Double monto_asegurado;
	
	@Column(name="detalles_adicionales", columnDefinition = "TEXT")
	private String detalles_adicionales;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	private Usuario usuario;

	//Constructor vacio, requerido por JPA  
	public Poliza() {
		
	}
	
	public Poliza(Long id_poliza, String tipo_seguro, LocalDate fecha_inicio, LocalDate fecha_vencimiento,
			Double monto_asegurado, String detalles_adicionales, Usuario usuario) {
		super();
		this.id_poliza = id_poliza;
		this.tipo_seguro = tipo_seguro;
		this.fecha_inicio = fecha_inicio;
		this.fecha_vencimiento = fecha_vencimiento;
		this.monto_asegurado = monto_asegurado;
		this.detalles_adicionales = detalles_adicionales;
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
		
}
