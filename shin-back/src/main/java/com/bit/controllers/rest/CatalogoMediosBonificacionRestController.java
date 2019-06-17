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
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoMediosBonificacionService;

@RestController
@RequestMapping("/catalogoMediosBonificacion")
public class CatalogoMediosBonificacionRestController {
	
	private static final Logger log= LoggerFactory.getLogger(CatalogoMediosBonificacionRestController.class);

	@Autowired
	private CatalogoMediosBonificacionService catalogoMediosBonificacionService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getCatalogoMediosBonificaciones() {
		
		log.info("Entrando a getCatalogoMediosBonificacion");
		ListItemsRSP rsp = catalogoMediosBonificacionService.getCatalogoMediosBonificacion();

		return rsp;
	}

	@PostMapping(value = "/catalogoMediosBonificacion/guardar")
	public SimpleResponse guardarCatalogoMediosBonificacion(@RequestBody CatalogoMediosBonificacion item) {
		
		log.info("Entrando a guardarCatalogoMediosBonificacion");
		SimpleResponse rsp = catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);
		
		return rsp;
	}

	@PostMapping(value = "/catalogoMediosBonificacion/actualizar")
	public SimpleResponse actualizarCatalogoMediosBonificacion(@RequestBody CatalogoMediosBonificacion item) {
		
		log.info("Entrando a actualizarCatalogoMediosBonificacion");
		SimpleResponse rsp = catalogoMediosBonificacionService.actualizarCatalogoMediosBonificacion(item);
		
		return rsp;
	}
}
