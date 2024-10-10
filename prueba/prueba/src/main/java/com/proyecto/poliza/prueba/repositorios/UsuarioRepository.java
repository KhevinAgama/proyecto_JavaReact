package com.proyecto.poliza.prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.poliza.prueba.entidad.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
