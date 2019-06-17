package com.bit.controllers.rest;

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
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.MediosBonificacionService;

@RestController
@RequestMapping("/mediosBonificacion")
public class MediosBonificacionRestController {
	
	private static final Logger log= LoggerFactory.getLogger(MediosBonificacionRestController.class);

	@Autowired
	private MediosBonificacionService mediosBonificacionService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getMediosBonificacion() {
		
		log.info("Entrando a getMediosBonificacion");
		ListItemsRSP rsp = mediosBonificacionService.getMediosBonificacion();
		
		return rsp;
	}
	
	@PostMapping(value = "/mediosBonificacion/guardar")
	public SimpleResponse guardarMediosBonificacion(@RequestBody MediosBonificacion item) {
		
		log.info("Entrando a guardarMediosBonificacion");
		SimpleResponse rsp= mediosBonificacionService.guardarMediosBonificacion(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/mediosBonificacion/actualizar")
	public SimpleResponse actualizarMediosBonificacion(@RequestBody MediosBonificacion item) {
		
		log.info("Entrando a actualizarMediosBonificacion");
		SimpleResponse rsp = mediosBonificacionService.actualizarMediosBonificacion(item);
		
		return rsp;
	}
}
