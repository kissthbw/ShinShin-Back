package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.PagoPayPal;
import com.bit.service.PagoPayPalService;

@RestController
@RequestMapping("/pagoPayPal")
public class PagoPayPalRestController {

	@Autowired
	private PagoPayPalService pagoPayPalService;

	@GetMapping(value = "/list")
	public @ResponseBody List<PagoPayPal> getPagosPayPal() {
		System.out.println("Get PagoPayPal");
		List<PagoPayPal> list = pagoPayPalService.getPagosPayPal();

		return list;
	}

	@PostMapping(value = "/pagoPayPal/guardar")
	public void guardarPagoPayPal(@RequestBody PagoPayPal item) {
		pagoPayPalService.guardarPagosPayPal(item);
	}

	@PostMapping(value = "/pagoPayPal/actualizar")
	public void actualizarPagoPayPal(@RequestBody PagoPayPal item) {
		pagoPayPalService.actualizarPagosPayPal(item);
	}
}
