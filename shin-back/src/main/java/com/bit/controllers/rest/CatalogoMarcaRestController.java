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
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoMarcaService;

@RestController
@RequestMapping("/marcas")
public class CatalogoMarcaRestController {

	@Autowired
	private CatalogoMarcaService catalogoMarcaService;

	@GetMapping(value = "/list")
	public @ResponseBody List<CatalogoMarca> getCatalogoMarcas() {
		System.out.println("Get Marcas");
		List<CatalogoMarca> list = catalogoMarcaService.getCatalogoMarca();

		return list;
	}

	@PostMapping(value = "/marca/registrar")
	public @ResponseBody SimpleResponse registrarMarcas(@RequestBody CatalogoMarca item) {
		SimpleResponse rsp = catalogoMarcaService.registrarMarcas(item);

		return rsp;
	}

	@PostMapping(value = "/marca/actualizar")
	public @ResponseBody SimpleResponse actualizarMarcas(@RequestBody CatalogoMarca item) {
		SimpleResponse rsp = catalogoMarcaService.actualizarMarcas(item);
		
		return rsp;
	}
}
