package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.CatalogoMediosBonificacion;
import com.bit.service.CatalogoMediosBonificacionService;

@RestController
@RequestMapping("/catalogoMediosBonificacion")
public class CatalogoMediosBonificacionRestController {

	@Autowired
	private CatalogoMediosBonificacionService catalogoMediosBonificacionService;

	@GetMapping(value = "list")
	public @ResponseBody List<CatalogoMediosBonificacion> getCatalogoMediosBonificaciones() {
		System.out.println("Get CatalogoMediosBonificaciones");
		List<CatalogoMediosBonificacion> list = catalogoMediosBonificacionService.getCatalogoMediosBonificacion();

		return list;
	}

	@PostMapping(value = "/catalogoMediosBonificacion/guardar")
	public void guardarCatalogoMediosBonificacion(@RequestBody CatalogoMediosBonificacion item) {
		catalogoMediosBonificacionService.guardarCatalogoMediosBonificacion(item);
	}

	@PostMapping(value = "/catalogoMediosBonificacion/actualizar")
	public void actualizarCatalogoMediosBonificacion(@RequestBody CatalogoMediosBonificacion item) {
		catalogoMediosBonificacionService.actualizarCatalogoMediosBonificacion(item);
	}
}
