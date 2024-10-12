package com.proyecto.poliza.prueba.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/crearUsuario")
	public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
		return new ResponseEntity<>(nuevoUsuario,HttpStatus.CREATED);
	}
	
	@GetMapping("/obtenerUsuarios")
	public List<Usuario> obtenerUsuarios(){
		return usuarioService.obtenerUsuarios();
	}
	
	@GetMapping("/obtenerUsuarios/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
		Usuario usuario = usuarioService.ObtenerUsuarioPorId(id);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/actualizarUsuario/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioActualizado =usuarioService.actualizarUsuario(id, usuario); 
		return ResponseEntity.ok(usuarioActualizado);
	}
	
	@DeleteMapping("/eliminarUsuario/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
		usuarioService.eliminarUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
}
