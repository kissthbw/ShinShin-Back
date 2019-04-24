package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Marca;
import com.bit.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaRestController {

	@Autowired
	private MarcaService marcaService;

	@GetMapping(value = "/list")
	public @ResponseBody List<Marca> getMarcas() {
		System.out.println("Get Marcas");
		List<Marca> list = marcaService.getMarca();

		return list;
	}

	@PostMapping(value = "/marcas/guardar")
	public void guardarMarcas(@RequestBody Marca item) {
		marcaService.guardarMarcas(item);
	}

	@PostMapping(value = "/marcas/actualizar")
	public void actualizarMarcas(@RequestBody Marca item) {
		marcaService.actualizarMarcas(item);
	}
}
