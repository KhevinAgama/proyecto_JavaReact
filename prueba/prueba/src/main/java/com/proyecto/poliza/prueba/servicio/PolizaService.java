package com.proyecto.poliza.prueba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.proyecto.poliza.prueba.dto.PolizaDTO;
import com.proyecto.poliza.prueba.entidad.Poliza;
import com.proyecto.poliza.prueba.entidad.Usuario;
import com.proyecto.poliza.prueba.exception.BadRequestException;
import com.proyecto.poliza.prueba.exception.ResourceNotFoundException;
import com.proyecto.poliza.prueba.repositorios.PolizaRepository;
import com.proyecto.poliza.prueba.repositorios.UsuarioRepository;

import jakarta.transaction.Transactional;

//Capa Negocio
@Service
public class PolizaService {
	
	@Autowired
	private PolizaRepository polizaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	//private static final Logger logger = LoggerFactory.getLogger(PolizaService.class);
	
	public PolizaService(PolizaRepository polizaRepository, UsuarioRepository usuarioRepository) {
		this.polizaRepository = polizaRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	//Creo mi Poliza y guardo en mi lista
	@Transactional //si hay un error en el codigo, revierte los cambios en la BD 
	public PolizaDTO crearPoliza(PolizaDTO polizaDTO) {
	
		//Validacion de nulo
		if(polizaDTO.getId_usuario() == null) {
			throw new BadRequestException("El ID del usuario no debe ser null.");
		}else if (polizaDTO.getTipo_seguro() == null) {
			throw new BadRequestException("El Tipo de Seguro no debe ser null.");
		}else if (polizaDTO.getFecha_inicio() == null) {
			throw new BadRequestException("La Fecha de inicio no debe ser null.");
		}else if (polizaDTO.getFecha_vencimiento() == null) {
			throw new BadRequestException("La Fecha de vencimiento no debe ser null.");
		}else if (polizaDTO.getMonto_asegurado() == null) {
			throw new BadRequestException("El Monto Asegurado no debe ser null.");
		}
		
		//Validacion  de que la fecha vencimiento sea posterior a la fecha inicio
		if(polizaDTO.getFecha_vencimiento().isBefore(polizaDTO.getFecha_inicio())) {
			throw new BadRequestException("La fecha de vencimiento debe ser posterior a la fecha de inicio.");
		}
		
		//Validacion de usuario existente
		Usuario usuario = usuarioRepository.findById(polizaDTO.getId_usuario()).
				orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
						"El usuario con ID " + polizaDTO.getId_usuario() + " no existe."));
		
		//System.out.println(polizaDTO.getTipo_seguro());
		
		//validacion de tipo de Seguro
		if(polizaDTO.getTipo_seguro().equalsIgnoreCase("auto") || 
				polizaDTO.getTipo_seguro().equalsIgnoreCase("inmueble") || 
						polizaDTO.getTipo_seguro().equalsIgnoreCase("celular")) {

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
			
		}else {
			throw new BadRequestException("El Tipo de Seguro debe ser auto, inmueble o celular.");
		}
	
	}
	
	//Listar Polizas
	public List<PolizaDTO> consultarPolizas(){
		return polizaRepository.findAll().stream().map(PolizaDTO::new).toList();
	}
	
	//Buscar por id
	public PolizaDTO obtenerPolizaPorId(Long id) {
		return polizaRepository.findById(id)
					.map(this::convertirEntidadDTO)
					.orElseThrow(() ->
					new ResourceNotFoundException("Poliza no encontrada."));
		
	}
	
	//actualizar Polizas
	@Transactional //si hay un error en el codigo, revierte los cambios en la BD
	public PolizaDTO actualizarPoliza(Long id, PolizaDTO polizaDTOActualizada) {
		//Validacion de ID Poliza
		Poliza poliza = polizaRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Póliza no encontrada con el ID: "+ id));
					//Validacion de nulo
		if(polizaDTOActualizada.getId_usuario() == null) {
			throw new BadRequestException("El ID del usuario no debe ser null.");
		}else if (polizaDTOActualizada.getTipo_seguro() == null) {
			throw new BadRequestException("El Tipo de Seguro no debe ser null.");
		}else if (polizaDTOActualizada.getFecha_inicio() == null) {
			throw new BadRequestException("La Fecha de inicio no debe ser null.");
		}else if (polizaDTOActualizada.getFecha_vencimiento() == null) {
			throw new BadRequestException("La Fecha de vencimiento no debe ser null.");
		}else if (polizaDTOActualizada.getMonto_asegurado() == null) {
			throw new BadRequestException("El Monto Asegurado no debe ser null.");
		}
				
		//Validacion de existencia de usuario	
		if(polizaDTOActualizada.getId_usuario() != null) {
			Usuario usuario = usuarioRepository.findById(polizaDTOActualizada.getId_usuario()).orElseThrow(
					() -> new BadRequestException("El usuario con ID "+ polizaDTOActualizada.getId_usuario()
					+" no existe."));
			poliza.setUsuario(usuario);
		}
			
		//Validacion de fecha de vencimiento
		if(polizaDTOActualizada.getFecha_vencimiento().isBefore(polizaDTOActualizada.getFecha_inicio())) {
			throw new BadRequestException("La fecha de vencimiento debe ser posterior a la fecha de inicio.");
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
					-> new ResourceNotFoundException("Poliza no encontrada."));
			//polizaRepository.delete(poliza);
		polizaRepository.deleteById(id);
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
