package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoProductos")
public class TipoProductoRestController {
	@GetMapping(value = "/list")
	public void getTipoProductos() {
		System.out.println();
	}

	@PostMapping(value = "/altaTipoProductos")
	public void altaTipoProductos() {
		System.out.println("Post TipoProductos");
	}

	@PutMapping(value = "/actualizaTipoProductos")
	public void actializaTipoProductos() {
		System.out.println("Put TipoProductos");
	}

	@DeleteMapping(value = "/eliminaTipoProductos")
	public void eliminaTipoProducto() {
		System.out.println("Delete TipoProductos");
	}
}
