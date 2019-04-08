package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valoracion")
public class ValoracionRestController {
	@GetMapping(value = "/list")
	public void getValoracion() {
		System.out.println("Get Valoracion");
	}

	@PostMapping(value = "/altaValoracion")
	public void altaValoracion() {
		System.out.println("Post Valoracion");
	}

	@PutMapping(value = "/actualizaValoracion")
	public void actuzalizaValoracion() {
		System.out.println("Put Valoracion");
	}
}
