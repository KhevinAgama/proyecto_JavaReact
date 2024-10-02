package com.proyecto.poliza.prueba.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.poliza.prueba.Entidad.Poliza;
import com.proyecto.poliza.prueba.exception.PolizaNotFoundException;

//Capa Negocio
@Service
public class PolizaService {
	
	private List<Poliza> polizas = new ArrayList<>();  
	private Long currentId = 1L;
	
	//Creo mi Poliza y guardo en mi lista
	public Poliza crearPoliza(Poliza poliza) {
		poliza.setIdPoliza(currentId++);
		polizas.add(poliza);
		return poliza;
	}
	
	//Listar Polizas
	public List<Poliza> consultarPolizas(){
		return polizas;
	}
	
	//actualizar Polizas
	public Poliza actualizarPoliza(Long id, Poliza polizaActualizada) {
		Optional<Poliza> polizaOptional = polizas.stream().filter(p -> p.getIdPoliza().equals(id)).findFirst();		
		if(! polizaOptional.isPresent()) {
			//throw new EntityNotFoundException(""); --alternativa por el JPA
			throw new PolizaNotFoundException("Poliza no encontrada con el ID "+ id);
		}
		
		Poliza poliza = polizaOptional.get();
		
		poliza.setFechaInicio(polizaActualizada.getFechaInicio());
		poliza.setFechaVencimiento(polizaActualizada.getFechaVencimiento());
		poliza.setMontoAsegurado(polizaActualizada.getMontoAsegurado());
		poliza.setDetallesAdicionales(polizaActualizada.getDetallesAdicionales());
		
		return poliza;
	}
	
	//eliminar Poliza
	public void eliminarPoliza(Long id) {
		boolean delete = polizas.removeIf(polizaEliminar -> polizaEliminar.getIdPoliza().equals(id));
		if(!delete) {
			throw new PolizaNotFoundException("No se encuentra registrada la ID: " + id);
		}
	}
	
	
}
