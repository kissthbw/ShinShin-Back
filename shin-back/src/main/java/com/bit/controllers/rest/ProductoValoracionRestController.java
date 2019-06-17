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

import com.bit.model.ProductoValoracion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProductoValoracionService;

@RestController
@RequestMapping("/productosValoracion")
public class ProductoValoracionRestController {
	
	private static final Logger log= LoggerFactory.getLogger(ProductoValoracionRestController.class);

	@Autowired
	private ProductoValoracionService productoValoracionService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getProductosValoracion() {
		
		log.info("Entrando a getProductosValoracion");
		ListItemsRSP rsp = productoValoracionService.getProductosValoracion();

		return rsp;
	}

	@PostMapping(value = "/productoValoracion/guardar")
	public SimpleResponse guardarProductoValoracion(@RequestBody ProductoValoracion item) {
		
		log.info("Entrando a guardarProductosValoracion");
		SimpleResponse rsp = productoValoracionService.guardarProductosValoracion(item);
		
		return rsp;
	}

	@PostMapping(value = "/productoValoracion/actualizar")
	public SimpleResponse actualizarProductoValoracion(@RequestBody ProductoValoracion item) {
		
		log.info("Entrando a actualizarProductosValoracion");
		SimpleResponse rsp = productoValoracionService.actualizarProductosValoracion(item);
		
		return rsp;
	}
}
