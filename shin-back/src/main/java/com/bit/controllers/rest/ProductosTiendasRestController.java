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

import com.bit.model.ProductosTiendas;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProductosTiendasService;

@RestController
@RequestMapping("/productosTiendas")
public class ProductosTiendasRestController {

	private static final Logger log = LoggerFactory.getLogger(ProductosTiendasRestController.class);

	@Autowired
	private ProductosTiendasService productosTiendasService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getProductosTiendas() {

		log.info("Entrando a getProductosTiendas");
		ListItemsRSP rsp = productosTiendasService.getProductosTiendas();

		return rsp;
	}

	@PostMapping(value = "/productoTienda/registrar")
	public @ResponseBody SimpleResponse registrarProductosPorTiendas(@RequestBody ProductosTiendas item) {

		log.info("Registrando un producto por tienda en registrarProductosPorTiendas");
		SimpleResponse rsp = productosTiendasService.registrarProductosPorTienda(item);

		return rsp;
	}

	@PostMapping(value = "/productoTienda/actualizar")
	public @ResponseBody SimpleResponse actualizarProductosPorTiendas(@RequestBody ProductosTiendas item) {

		log.info("Actualizando un producto por tienda en registrarProductosPorTiendas");
		SimpleResponse rsp = productosTiendasService.actualizarProductosPorTienda(item);

		return rsp;
	}
}
