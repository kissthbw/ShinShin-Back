package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paypal")
public class PayPalRestController {

	@GetMapping(value = "/list")
	public void getPaypal() {
		System.out.println("Get Paypal");
	}

	@PostMapping(value = "/altaPaypal")
	public void altaPayPal() {
		System.out.println("Post PayPal");
	}

	@PutMapping(value = "/actualuzaPayPal")
	public void actualizaPayPal() {
		System.out.println("Put PayPal");
	}

	@DeleteMapping(value = "/eliminaPayPal")
	public void eliminaPayPal() {
		System.out.println("Delete PayPal");
	}

	@PutMapping(value = "/recargaPayPal")
	public void recargaPaypal() {
		System.out.println("Put RecargapayPal");
	}
}
