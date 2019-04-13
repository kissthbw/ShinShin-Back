package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Valoracion;
import com.bit.service.ValoracionService;

@RestController
@RequestMapping("/valoracion")
public class ValoracionRestController {

	@Autowired
	private ValoracionService valoracionService;

	@GetMapping(value = "/valoracion/list")
	public @ResponseBody List<Valoracion> getValoraciones() {
		System.out.println("Get Valoraciones");
		List<Valoracion> list = valoracionService.getValoraciones();

		return list;
	}

	@PostMapping(value = "/valoracion/guardar")
	public void guardarValoracion(@RequestBody Valoracion item) {
		valoracionService.guardarValoraciones(item);
	}

	@PostMapping(value = "valoracion/actualizar")
	public void actualizarValoracion(@RequestBody Valoracion item) {
		valoracionService.actualizarValoraciones(item);
	}
}
