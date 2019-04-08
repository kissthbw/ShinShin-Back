package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagoMovil")
public class MovilRestController {
	@GetMapping(value = "/list")
	public void getMovil() {
		System.out.println("Get Movil");
	}
	
	@PostMapping(value = "/altaMovil")
	public void altaMovil() {
		System.out.println("Post Movil");
	}
	
	@PutMapping(value = "/actualizaMovil")
	public void actualizaMovil() {
		System.out.println("Put Movil");
	}
	
	@DeleteMapping(value = "/eliminaMovil")
	public void eliminaMovil() {
		System.out.println("Delete Movil");
	}
	@PutMapping(value = "/recargaSaldo")
	public void recargaSaldo() {
		System.out.println("Put RecargaSaldo");
	}
}
