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

import com.bit.model.HistoricoMediosBonificacion;
import com.bit.service.HistoricoMediosBonificacionService;

@RestController
@RequestMapping("/historicoMediosBonificacion")
public class HistoricoMediosBonificacionRestController {
	
	private static final Logger log= LoggerFactory.getLogger(HistoricoMediosBonificacionRestController.class);
	
	@Autowired
	private HistoricoMediosBonificacionService historicoMediosBonificacionService;

	@GetMapping(value = "/list")
	public @ResponseBody List<HistoricoMediosBonificacion> getHistoricosMediosBonificacion() {
		
		log.info("Entrando a getHistoricosMediosBonificacion");
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionService.getHistoricosMediosBonificacion();

		return list;
	}

	@PostMapping("/historicoMediosBonificacion/guardar")
	public void guardarHistoricoMediosBonificacion(@RequestBody HistoricoMediosBonificacion item) {
		
		log.info("Entrando a guardarHistoricosMediosBonificacion");
		historicoMediosBonificacionService.guardarHistoricosMediosBonificacion(item);
	}

	@PostMapping("/historicoMediosBonificacion/actualizar")
	public void actualizarHistoricoMediosBonificacion(@RequestBody HistoricoMediosBonificacion item) {
		
		log.info("Entrando a actualizarHistoricosMediosBonificacion");
		historicoMediosBonificacionService.actualizarHistoricosMediosBonificacion(item);
	}
}
