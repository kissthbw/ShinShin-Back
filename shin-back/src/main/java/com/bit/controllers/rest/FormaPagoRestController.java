package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formaPago")
public class FormaPagoRestController {
	@GetMapping(value = "/list")
	public void getFormaPago() {
		System.out.println("Get FormaPago");
	}

	@PostMapping(value = "/altaFormaPago")
	public void altaFormaPago() {
		System.out.println("Post FormaPago");
	}

	@PutMapping(value = "/actualizaFormaPago")
	public void actualizaFormaPago() {
		System.out.println("Put FprmaPago");
	}

	@DeleteMapping(value = "/eliminaFormaPago")
	public void eliminaFormaPago() {
		System.out.println("Delete FormaPago");
	}
}
