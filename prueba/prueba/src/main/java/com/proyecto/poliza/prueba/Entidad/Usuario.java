package com.proyecto.poliza.prueba.entidad;

import jakarta.persistence.*;


@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_usuario")
	private Long id_usuario;
	
	@Column(name= "nombre", nullable = false)
	private String nombre;
	
	@Column(name= "correo", nullable = false)
	private String correo;
	
	@Column(name= "dni", nullable = false, length = 8)
	private String dni;
	
	//Constructor vacio, requerido por JPA
	public Usuario() {
	}
	
	public Usuario(Long id_usuario, String nombre, String correo, String dni) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.correo = correo;
		this.dni = dni;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}	
	
	
}
