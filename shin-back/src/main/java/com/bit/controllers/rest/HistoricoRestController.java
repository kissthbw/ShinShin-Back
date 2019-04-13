package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Historico;
import com.bit.service.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoRestController {

	@Autowired
	private HistoricoService historicoService;

	@GetMapping(value = "/list")
	public @ResponseBody List<Historico> getHistoricos() {
		System.out.println("Get Historico");
		List<Historico> list = historicoService.getHistoricos();

		return list;
	}

	@PostMapping(value = "/historico/guardar")
	public void guardarHistorico(@RequestBody Historico item) {
		historicoService.guardarHistoricos(item);
	}
}
