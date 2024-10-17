package com.proyecto.poliza.prueba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.poliza.prueba.entidad.Usuario;
import com.proyecto.poliza.prueba.exception.BadRequestException;
import com.proyecto.poliza.prueba.exception.ResourceNotFoundException;
import com.proyecto.poliza.prueba.repositorios.UsuarioRepository;

import org.springframework.transaction.annotation.Transactional;
//import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	//Crear usuario
	@Transactional //si hay un error en el codigo, revierte los cambios en la BD
	public Usuario crearUsuario(Usuario usuario) {
		//Validacion de nulo
		if(usuario.getDni() == null) {
			throw new BadRequestException("El DNI del usuario no debe ser nulo.");
		}else if (usuario.getCorreo() == null) {
			throw new BadRequestException("El Correo del usuario no debe ser nulo.");
		}else if (usuario.getNombre() == null) {
			throw new BadRequestException("El Nombre del usuario no debe ser nulo.");
		}
		
		//Validacion si el nombre ya existe
		if(usuarioRepository.findByNombre(usuario.getNombre()).isPresent()) {
			throw new BadRequestException("El Nombre del usuario ya está registrado.");
		}
			
		//Validacion si el correo ya existe
		if(usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
			throw new BadRequestException("El Correo del usuario ya está registrado.");
		}
		
		//Validacion si el DNI ya existe
		if(usuarioRepository.findByDni(usuario.getDni()).isPresent()) {
			throw new BadRequestException("El DNI del usuario ya está registrado.");
		}
		
		return usuarioRepository.save(usuario);
			
	}
	
	public List<Usuario> obtenerUsuarios() {		
		return usuarioRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Usuario ObtenerUsuarioPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Usuario no encontrado"));
	}
	
	@Transactional
	public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Usuario no encontrado."));
		
		//Validacion de nulo
		if(usuarioActualizado.getDni() == null) {
			throw new BadRequestException("El DNI del usuario no debe ser nulo.");
		}else if (usuarioActualizado.getCorreo() == null) {
			throw new BadRequestException("El Correo del usuario no debe ser nulo.");
		}else if (usuarioActualizado.getNombre() == null) {
			throw new BadRequestException("El Nombre del usuario no debe ser nulo.");
		}
		
		usuario.setNombre(usuarioActualizado.getNombre());
		usuario.setCorreo(usuarioActualizado.getCorreo());
		usuario.setDni(usuarioActualizado.getDni());
			
		return usuarioRepository.save(usuario);
				
	}
	
	public void eliminarUsuario(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Usuario no encontrado"));
		usuarioRepository.delete(usuario);
	}	
}
