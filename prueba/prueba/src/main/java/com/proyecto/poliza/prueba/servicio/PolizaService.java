package com.proyecto.poliza.prueba.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.poliza.prueba.dto.PolizaDTO;
import com.proyecto.poliza.prueba.entidad.Poliza;
import com.proyecto.poliza.prueba.entidad.Usuario;
import com.proyecto.poliza.prueba.exception.PolizaNotFoundException;
import com.proyecto.poliza.prueba.repositorios.PolizaRepository;
import com.proyecto.poliza.prueba.repositorios.UsuarioRepository;

//Capa Negocio
@Service
public class PolizaService {
	
	@Autowired
	private PolizaRepository polizaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public PolizaService(PolizaRepository polizaRepository, UsuarioRepository usuarioRepository) {
		this.polizaRepository = polizaRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	//Creo mi Poliza y guardo en mi lista
	public PolizaDTO crearPoliza(PolizaDTO polizaDTO) {
		
		//Validacon de usuario nulo
		if(polizaDTO.getId_usuario() == null) {
			throw new RuntimeException("El ID del usuario no debe ser null.");
		}
		
		//Validacion de usuario existente
		Usuario usuario = usuarioRepository.findById(polizaDTO.getId_usuario()).
				orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + polizaDTO.getId_usuario()));
		
		//Crear nueva poliza
		Poliza poliza = new Poliza();
		
		poliza.setTipo_seguro(polizaDTO.getTipo_seguro());
		poliza.setFecha_inicio(polizaDTO.getFecha_inicio());
		poliza.setFecha_vencimiento(polizaDTO.getFecha_vencimiento());
		poliza.setMonto_asegurado(polizaDTO.getMonto_asegurado());
		poliza.setDetalles_adicionales(polizaDTO.getDetalles_adicionales());
		
		poliza.setUsuario(usuario);
		
		Poliza polizaGuardadaPoliza = polizaRepository.save(poliza);
		
		return convertirEntidadDTO(polizaGuardadaPoliza);
	}
	
	//Listar Polizas
	public List<PolizaDTO> consultarPolizas(){
		return polizaRepository.findAll().stream().map(PolizaDTO::new).toList();
	}
	
	//Buscar por id
	public Poliza obtenerPolizaPorId(Long id) {
		return polizaRepository.findById(id).orElseThrow(()
				-> new RuntimeException("Poliza no encontrada."));
	}
	
	//actualizar Polizas
	public PolizaDTO actualizarPoliza(Long id, PolizaDTO polizaDTOActualizada) {
		
		
		Poliza poliza = polizaRepository.findById(id).orElseThrow(()
				-> new IllegalArgumentException("Poliza no encontrada con el ID: "+ id));
		
		if(polizaDTOActualizada.getId_usuario() != null) {
			Usuario usuario = usuarioRepository.findById(polizaDTOActualizada.getId_usuario()).orElseThrow(
					() -> new IllegalArgumentException("El usuario con ID: "+ polizaDTOActualizada.getId_usuario()
					+"no existe."));
			poliza.setUsuario(usuario);
		}
		
		poliza.setTipo_seguro(polizaDTOActualizada.getTipo_seguro());
		poliza.setFecha_inicio(polizaDTOActualizada.getFecha_inicio());
		poliza.setFecha_vencimiento(polizaDTOActualizada.getFecha_vencimiento());
		poliza.setMonto_asegurado(polizaDTOActualizada.getMonto_asegurado());
		poliza.setDetalles_adicionales(polizaDTOActualizada.getDetalles_adicionales());
		//poliza.setUsuario(polizaActualizada.getUsuario());
		Poliza polizaActualizada = polizaRepository.save(poliza);
		
		return new PolizaDTO(polizaActualizada);
	}
	
	//eliminar Poliza
	public void eliminarPoliza(Long id) {
		Poliza poliza = polizaRepository.findById(id).orElseThrow(()
				-> new RuntimeException("Poliza no encontrada."));
		polizaRepository.delete(poliza);
	}
	
	
	//metodo para convertir entidad Poliza a DTO
	private PolizaDTO convertirEntidadDTO(Poliza poliza) {
		PolizaDTO polizaDTO = new PolizaDTO();
		
		polizaDTO.setId_poliza(poliza.getId_poliza());
		polizaDTO.setTipo_seguro(poliza.getTipo_seguro());
		polizaDTO.setFecha_inicio(poliza.getFecha_inicio());
		polizaDTO.setFecha_vencimiento(poliza.getFecha_vencimiento());
		polizaDTO.setMonto_asegurado(poliza.getMonto_asegurado());
		polizaDTO.setDetalles_adicionales(poliza.getDetalles_adicionales());
		polizaDTO.setId_usuario(poliza.getUsuario().getId_usuario());
			
		return polizaDTO;
	}
	
	
}
