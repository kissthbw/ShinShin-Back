package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Bonificacion;
import com.bit.service.BonificacionService;

@RestController
@RequestMapping("/bonificacion")
public class BonificacionRestController {

	@Autowired
	private BonificacionService bonificacionService;

	@GetMapping(value = "/list")
	public @ResponseBody List<Bonificacion> getBonificaciones() {
		System.out.println("Get Bonificacion");
		List<Bonificacion> list = bonificacionService.getBonificaciones();

		return list;
	}

	@PostMapping(value = "/bonificacion/guardar")
	public void guardarBonificacion(@RequestBody Bonificacion item) {
		bonificacionService.guardarBonificaciones(item);
	}

	@PostMapping(value = "/bonificacion/actualizar")
	public void actualizarBonificacion(@RequestBody Bonificacion item) {
		bonificacionService.actualizarBonificaciones(item);
	}
}
