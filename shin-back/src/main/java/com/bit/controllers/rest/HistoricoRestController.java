package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HistoricoRestController {
	
	@GetMapping("/historico")
	public void getHistorico() {
		System.out.println("Get Historico");
	}
}
