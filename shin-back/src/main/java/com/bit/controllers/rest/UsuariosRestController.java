package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosRestController {

	@GetMapping(value = "/list")
	public void getUsuarios() {
		System.out.println("Get Usuarios");
	}
	
	@PostMapping(value = "/altaUsuario")
	public void altaUsuario() {
		System.out.println();
	}
	
	
}