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
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosRestController {

	//autowired inyecta (crea) de tipo usuarioservice
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
	public @ResponseBody SimpleResponse activarUsuario(@RequestBody Usuario item) {
		SimpleResponse rsp = usuarioService.activarUsuarios(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/usuario/actualizar")
	public @ResponseBody SimpleResponse actualizarUsuario(@RequestBody Usuario item) {
		SimpleResponse rsp = usuarioService.actualizarUsuarios(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/usuario/login")
	public @ResponseBody Usuario findUserByUser(@RequestBody Usuario item) {
		Usuario user = usuarioService.findUserByUser(item);
		
		return user;
	}
	
	@PostMapping(value = "/usuario/login2")
	public @ResponseBody Usuario findUserByUserAndPassword(@RequestBody Usuario item) {
		Usuario user = usuarioService.findUserByUserAndPassword(item);
		
		return user;
	}
	
	@GetMapping(value = "/usuario/totalBonificacion")
	public @ResponseBody InformacionUsuarioRSP obtenerTotalBonificacion(@RequestBody Usuario item) {
		InformacionUsuarioRSP user = usuarioService.obtenerTotalBonificacion(item);
		
		return user;
	}
}