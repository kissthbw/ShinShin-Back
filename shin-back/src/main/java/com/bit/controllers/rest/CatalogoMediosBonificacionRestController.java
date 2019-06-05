package com.bit.controllers.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger log= LoggerFactory.getLogger(CatalogoMediosBonificacionRestController.class);

	@Autowired
	private CatalogoMediosBonificacionService catalogoMediosBonificacionService;

	@GetMapping(value = "list")
	public @ResponseBody List<CatalogoMediosBonificacion> getCatalogoMediosBonificaciones() {
		
		log.info("Entrando a getCatalogoMediosBonificacion");
		List<CatalogoMediosBonificacion> list = catalogoMediosBonificacionService.getCatalogoMediosBonificacion();

		return list;
	}

	@PostMapping(value = "/catalogoMediosBonificacion/guardar")
	public void guardarCatalogoMediosBonificacion(@RequestBody CatalogoMediosBonificacion item) {
		
		log.info("Entrando a guardarCatalogoMediosBonificacion");
		catalogoMediosBonificacionService.guardarCatalogoMediosBonificacion(item);
	}

	@PostMapping(value = "/catalogoMediosBonificacion/actualizar")
	public void actualizarCatalogoMediosBonificacion(@RequestBody CatalogoMediosBonificacion item) {
		
		log.info("Entrando a actualizarCatalogoMediosBonificacion");
		catalogoMediosBonificacionService.actualizarCatalogoMediosBonificacion(item);
	}
}
