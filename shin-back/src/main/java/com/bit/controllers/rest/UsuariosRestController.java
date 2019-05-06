package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosRestController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/list")
	public @ResponseBody List<Usuario> getUsuarios() {
		System.out.println("Get Usuarios");
		List<Usuario> list = usuarioService.getUsuarios();

		return list;
	}

	@PostMapping(value = "/usuario/registrar")
	public @ResponseBody SimpleResponse registrarUsuario(@RequestBody Usuario item) {
		SimpleResponse rsp = usuarioService.registrarUsuarios(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/usuario/activar")
	public void activarUsuario(@RequestBody Usuario item) {
		usuarioService.activarUsuarios(item);
	}
	
	@PostMapping(value = "/usuario/actualizar")
	public void actualizarUsuario(@RequestBody Usuario item) {
		usuarioService.actualizarUsuarios(item);
	}
}