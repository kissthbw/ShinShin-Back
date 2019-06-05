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
import com.bit.service.CatalogoTipoProductoService;

@RestController
@RequestMapping("/catalogoTipoProductos")
public class CatalogoTipoProductoRestController {
	
	private static final Logger log= LoggerFactory.getLogger(CatalogoTipoProductoRestController.class);

	@Autowired
	private CatalogoTipoProductoService catalogoTipoProductoService;

	@GetMapping(value = "/list")
	public @ResponseBody List<CatalogoTipoProducto> getCatalogoTipoProductos() {
		
		log.info("Entrando a getCatalogoTipoProductos");
		List<CatalogoTipoProducto> list = catalogoTipoProductoService.getCatalogoTipoProductos();

		return list;
	}

	@PostMapping(value = "/catalogoTipoProductos/registrar")
	public void registrarTipoProductos(@RequestBody CatalogoTipoProducto item) {
		
		log.info("Entrando a registrarCatalogoTipoProductos");
		catalogoTipoProductoService.registrarCatalogoTipoProductos(item);
	}

	@PostMapping(value = "/catalogoTipoProductos/actualizar")
	public void actualizarTipoProductos(@RequestBody CatalogoTipoProducto item) {
		
		log.info("Entrando a actualizarCatalogoTipoProductos");
		catalogoTipoProductoService.actualizarCatalogoTipoProductos(item);
	}
}
