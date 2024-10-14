package com.proyecto.poliza.prueba.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.poliza.prueba.entidad.Usuario;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//validar si ya existen antes de guardar el nuevo usuario
	Optional<Usuario> findByNombre(String nombre);
	
	Optional<Usuario> findByCorreo(String correo);
	
	Optional<Usuario> findByDni(String dni);
}
