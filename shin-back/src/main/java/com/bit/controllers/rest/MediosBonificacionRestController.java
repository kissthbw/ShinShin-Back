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

import com.bit.model.MediosBonificacion;
import com.bit.service.MediosBonificacionService;

@RestController
@RequestMapping("/mediosBonificacion")
public class MediosBonificacionRestController {
	
	private static final Logger log= LoggerFactory.getLogger(MediosBonificacionRestController.class);

	@Autowired
	private MediosBonificacionService mediosBonificacionService;

	@GetMapping(value = "/list")
	public @ResponseBody List<MediosBonificacion> getMediosBonificacion() {
		
		log.info("Entrando a getMediosBonificacion");
		List <MediosBonificacion> list = mediosBonificacionService.getMediosBonificacion();
		
		return list;
	}
	
	@PostMapping(value = "/mediosBonificacion/guardar")
	public void guardarMediosBonificacion(@RequestBody MediosBonificacion item) {
		
		log.info("Entrando a guardarMediosBonificacion");
		mediosBonificacionService.guardarMediosBonificacion(item);
	}
	
	@PostMapping(value = "/mediosBonificacion/actualizar")
	public void actualizarMediosBonificacion(@RequestBody MediosBonificacion item) {
		
		log.info("Entrando a actualizarMediosBonificacion");
		mediosBonificacionService.actualizarMediosBonificacion(item);
	}
}
