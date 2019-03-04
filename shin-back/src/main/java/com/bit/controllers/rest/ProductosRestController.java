package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductosRestController {
	
	@GetMapping(value="/list")
	public void getProductos() {
		System.out.println( "Get Productos" );
	}
}
