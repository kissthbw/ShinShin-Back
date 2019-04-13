package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.PagoMovil;
import com.bit.service.PagoMovilService;

@RestController
@RequestMapping("/pagoMovil")
public class PagoMovilRestController {

	@Autowired
	private PagoMovilService pagoMovilService;

	@GetMapping(value = "/list")
	public @ResponseBody List<PagoMovil> getPagosMoviles() {
		System.out.println("Get PagoMovil");
		List<PagoMovil> list = pagoMovilService.getPagosMoviles();

		return list;
	}

	@PostMapping(value = "/pagoMovil/guardar")
	public void guardarPagoMovil(@RequestBody PagoMovil item) {
		pagoMovilService.guardarPagosMoviles(item);
	}

	@PostMapping(value = "/pagoMovil/actualizar")
	public void actualizarPagoMovil(@RequestBody PagoMovil item) {
		pagoMovilService.actualizarPagosMoviles(item);
	}
}
