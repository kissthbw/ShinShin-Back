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

import com.bit.model.CatalogoTienda;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoTiendaService;

@RestController
@RequestMapping("/catalogoTiendas")
public class CatalogoTiendaRestController {

	private static final Logger log = LoggerFactory.getLogger(CatalogoTiendaRestController.class);

	@Autowired
	private CatalogoTiendaService catalogoTiendaService;

	@GetMapping(value = "/list")
	public @ResponseBody List<CatalogoTienda> getCatalogoTienda() {
		
		log.info("Entrando a getCatalogTienda");
		
		List<CatalogoTienda> list = catalogoTiendaService.getCatalogoTienda();

		return list;
	}
	
	@PostMapping(value = "/tienda/registrar")
	public @ResponseBody SimpleResponse registrarTiendas(@RequestBody CatalogoTienda item) {
		
		log.info("Entrando a registrarTiendas");
		
		SimpleResponse rsp = catalogoTiendaService.registrarTiendas(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/tienda/actualizar")
	public @ResponseBody SimpleResponse actualizarTiendas(@RequestBody CatalogoTienda item) {
		
		log.info("Entrando a actualizarTiendas");
		
		SimpleResponse rsp = catalogoTiendaService.actualizarTiendas(item);
		
		return rsp;
	}
}
