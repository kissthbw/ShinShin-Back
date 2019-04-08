package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaRestController {
	@GetMapping(value = "/list")
	public void getPagoTarjeta() {
		System.out.println("Get Tarjeta");
	}

	@PostMapping(value = "/altaTarjeta")
	public void altaTarjeta() {
		System.out.println("Post Tarjeta");
	}

	@PutMapping(value = "/actualizaTarjeta")
	public void actualizaTarjeta() {
		System.out.println("Put Tarjeta");
	}

	@DeleteMapping(value = "/eliminaTarjeta")
	public void eliminaTarjeta() {
		System.out.println("Delete Tarjeta");
	}
	
	@PutMapping(value = "/recargaTarjeta")
	public void recargaTarjeta() {
		System.out.println("Put RecargaTarjeta");
	}
}
