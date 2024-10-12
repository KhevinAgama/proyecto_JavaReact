package com.proyecto.poliza.prueba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.proyecto.poliza.prueba.entidad.Usuario;
import com.proyecto.poliza.prueba.repositorios.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	//Crear usuario
	public Usuario crearUsuario(Usuario usuario) {
		
		//Validacion de nulo
		if(usuario.getDni() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El DNI del usuario no debe ser nulo.");
		}else if (usuario.getCorreo() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Correo del usuario no debe ser nulo.");
		}else if (usuario.getNombre() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Nombre del usuario no debe ser nulo.");
		}
		
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> obtenerUsuarios() {		
		return usuarioRepository.findAll();		
	}
	
	public Usuario ObtenerUsuarioPorId(Long id) {
		
		return usuarioRepository.findById(id).orElseThrow(()->
		new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));
	}
	
	public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
	
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
				-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado."));
		
		//Validacion de nulo
		if(usuarioActualizado.getDni() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El DNI del usuario no debe ser nulo.");
		}else if (usuarioActualizado.getCorreo() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Correo del usuario no debe ser nulo.");
		}else if (usuarioActualizado.getNombre() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Nombre del usuario no debe ser nulo.");
		}
		
		usuario.setNombre(usuarioActualizado.getNombre());
		usuario.setCorreo(usuarioActualizado.getCorreo());
		usuario.setDni(usuarioActualizado.getDni());
		
		return usuarioRepository.save(usuario);		
	}
	
	public void eliminarUsuario(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
				-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));
		
		usuarioRepository.delete(usuario);
	}
	
	
}
