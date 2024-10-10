package com.proyecto.poliza.prueba.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.poliza.prueba.entidad.Poliza;
import com.proyecto.poliza.prueba.exception.PolizaNotFoundException;
import com.proyecto.poliza.prueba.repositorios.PolizaRepository;

//Capa Negocio
@Service
public class PolizaService {
	
	@Autowired
	private final PolizaRepository polizaRepository;
	
	public PolizaService(PolizaRepository polizaRepository) {
		this.polizaRepository = polizaRepository;
	}
	
	//Creo mi Poliza y guardo en mi lista
	public Poliza crearPoliza(Poliza poliza) {
		return polizaRepository.save(poliza);
	}
	
	//Listar Polizas
	public List<Poliza> consultarPolizas(){
		return polizaRepository.findAll();
	}
	
	//Buscar por id
	public Poliza obtenerPolizaPorId(Long id) {
		return polizaRepository.findById(id).orElseThrow(()
				-> new RuntimeException("Poliza no encontrada."));
	}
	
	//actualizar Polizas
	public Poliza actualizarPoliza(Long id, Poliza polizaActualizada) {
		
		
		Poliza poliza = polizaRepository.findById(id).orElseThrow(()
				-> new RuntimeException("Poliza no encontrada."));
		
		poliza.setTipo_seguro(polizaActualizada.getTipo_seguro());
		poliza.setFecha_inicio(polizaActualizada.getFecha_inicio());
		poliza.setFecha_vencimiento(polizaActualizada.getFecha_vencimiento());
		poliza.setMonto_asegurado(polizaActualizada.getMonto_asegurado());
		poliza.setDetalles_adicionales(polizaActualizada.getDetalles_adicionales());
		
		return polizaRepository.save(poliza);
	}
	
	//eliminar Poliza
	public void eliminarPoliza(Long id) {
		Poliza poliza = polizaRepository.findById(id).orElseThrow(()
				-> new RuntimeException("Poliza no encontrada."));
		polizaRepository.delete(poliza);
	}
	
	
}
