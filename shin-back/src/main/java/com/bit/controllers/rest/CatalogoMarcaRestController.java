package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.CatalogoMarca;
import com.bit.service.CatalogoMarcaService;

@RestController
@RequestMapping("/marcas")
public class CatalogoMarcaRestController {

	@Autowired
	private CatalogoMarcaService marcaService;

	@GetMapping(value = "/list")
	public @ResponseBody List<CatalogoMarca> getMarcas() {
		System.out.println("Get Marcas");
		List<CatalogoMarca> list = marcaService.getMarca();

		return list;
	}

	@PostMapping(value = "/marcas/guardar")
	public void guardarMarcas(@RequestBody CatalogoMarca item) {
		marcaService.guardarMarcas(item);
	}

	@PostMapping(value = "/marcas/actualizar")
	public void actualizarMarcas(@RequestBody CatalogoMarca item) {
		marcaService.actualizarMarcas(item);
	}
}
