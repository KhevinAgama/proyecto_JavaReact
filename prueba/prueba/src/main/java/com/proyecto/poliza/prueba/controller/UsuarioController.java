package com.proyecto.poliza.prueba.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.poliza.prueba.entidad.Usuario;
import com.proyecto.poliza.prueba.servicio.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/crearUsuario")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return usuarioService.crearUsuario(usuario);
	}
	
	@GetMapping("/obtenerUsuarios")
	public List<Usuario> obtenerUsuarios(){
		return usuarioService.obtenerUsuarios();
	}
	
	@GetMapping("/obtenerUsuarios/{id}")
	public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
		return usuarioService.ObtenerUsuarioPorId(id);
	}
	
	@PutMapping("/actualizarUsuario/{id}")
	public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		return usuarioService.actualizarUsuario(id, usuario);
	}
	
	@DeleteMapping("/eliminarUsuario")
	public void eliminarUsuario(@PathVariable Long id) {
		usuarioService.eliminarUsuario(id);
	}
	
}
