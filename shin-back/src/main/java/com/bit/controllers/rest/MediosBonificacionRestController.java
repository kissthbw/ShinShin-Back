package com.bit.controllers.rest;

import java.util.List;

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

	@Autowired
	private MediosBonificacionService mediosBonificacionService;

	@GetMapping(value = "/list")
	public @ResponseBody List<MediosBonificacion> getMediosBonificacion() {
		System.out.println("Get MediosBonificacion");
		List <MediosBonificacion> list = mediosBonificacionService.getMediosBonificacion();
		
		return list;
	}
	
	@PostMapping(value = "/mediosBonificacion/guardar")
	public void guardarMediosBonificacion(@RequestBody MediosBonificacion item) {
		mediosBonificacionService.guardarMediosBonificacion(item);
	}
	
	@PostMapping(value = "/mediosBonificacion/actualizar")
	public void actualizarMediosBonificacion(@RequestBody MediosBonificacion item) {
		mediosBonificacionService.actualizarMediosBonificacion(item);
	}
}
