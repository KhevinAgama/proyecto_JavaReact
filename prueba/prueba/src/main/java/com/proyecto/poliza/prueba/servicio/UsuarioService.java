package com.proyecto.poliza.prueba.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.poliza.prueba.entidad.Usuario;
import com.proyecto.poliza.prueba.repositorios.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario crearUsuario(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> obtenerUsuarios() {
		
		return usuarioRepository.findAll();		
	}
	
	public Usuario ObtenerUsuarioPorId(Long id) {
		
		return usuarioRepository.findById(id).orElseThrow(()->
		new RuntimeException("Usuario no encontrado"));
	}
	
	public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
	
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
				-> new RuntimeException("Usuario no encontrado."));
		
		usuario.setNombre(usuarioActualizado.getNombre());
		usuario.setCorreo(usuarioActualizado.getCorreo());
		usuario.setDni(usuarioActualizado.getDni());
		
		return usuarioRepository.save(usuario);		
	}
	
	public void eliminarUsuario(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(()
				-> new RuntimeException("Usuario no encontrado"));
		
		usuarioRepository.delete(usuario);
	}
	
	
}
