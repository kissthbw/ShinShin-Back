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

import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoTipoProductoService;

@RestController
@RequestMapping("/catalogoTipoProductos")
public class CatalogoTipoProductoRestController {
	
	private static final Logger log= LoggerFactory.getLogger(CatalogoTipoProductoRestController.class);

	@Autowired
	private CatalogoTipoProductoService catalogoTipoProductoService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getCatalogoTipoProductos() {
		
		log.info("Entrando a getCatalogoTipoProductos");
		ListItemsRSP rsp = catalogoTipoProductoService.getCatalogoTipoProductos();

		return rsp;
	}

	@PostMapping(value = "/catalogoTipoProductos/registrar")
	public SimpleResponse registrarTipoProductos(@RequestBody CatalogoTipoProducto item) {
		
		log.info("Entrando a registrarCatalogoTipoProductos");
		SimpleResponse rsp = catalogoTipoProductoService.registrarCatalogoTipoProductos(item);
		
		return rsp;
	}

	@PostMapping(value = "/catalogoTipoProductos/actualizar")
	public SimpleResponse actualizarTipoProductos(@RequestBody CatalogoTipoProducto item) {
		
		log.info("Entrando a actualizarCatalogoTipoProductos");
		SimpleResponse rsp = catalogoTipoProductoService.actualizarCatalogoTipoProductos(item);
		
		return rsp;
	}
}
