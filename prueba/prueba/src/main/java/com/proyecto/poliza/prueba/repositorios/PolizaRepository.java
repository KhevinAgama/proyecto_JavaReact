package com.proyecto.poliza.prueba.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.poliza.prueba.entidad.Poliza;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long>{

}
