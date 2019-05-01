package com.bit.controllers.rest;

import java.util.List;

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

	@Autowired
	private HistoricoMediosBonificacionService historicoMediosBonificacionService;

	@GetMapping(value = "/list")
	public @ResponseBody List<HistoricoMediosBonificacion> getHistoricosMediosBonificacion() {
		System.out.println("Get HistoricoMediosBonificacion");
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionService.getHistoricosMediosBonificacion();

		return list;
	}

	@PostMapping("/historicoMediosBonificacion/guardar")
	public void guardarHistoricoMediosBonificacion(@RequestBody HistoricoMediosBonificacion item) {
		historicoMediosBonificacionService.guardarHistoricosMediosBonificacion(item);
	}

	@PostMapping("/historicoMediosBonificacion/actualizar")
	public void actualizarHistoricoMediosBonificacion(@RequestBody HistoricoMediosBonificacion item) {
		historicoMediosBonificacionService.actualizarHistoricosMediosBonificacion(item);
	}
}
