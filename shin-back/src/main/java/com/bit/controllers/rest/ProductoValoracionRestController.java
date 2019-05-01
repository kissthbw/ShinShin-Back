package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.ProductoValoracion;
import com.bit.service.ProductoValoracionService;

@RestController
@RequestMapping("/productosValoracion")
public class ProductoValoracionRestController {

	@Autowired
	private ProductoValoracionService productoValoracionService;

	@GetMapping(value = "/list")
	public @ResponseBody List<ProductoValoracion> getProductosValoracion() {
		System.out.println("Get Productos");
		List<ProductoValoracion> list = productoValoracionService.getProductosValoracion();

		return list;
	}

	@PostMapping(value = "/productoValoracion/guardar")
	public void guardarProductoValoracion(@RequestBody ProductoValoracion item) {
		productoValoracionService.guardarProductosValoracion(item);
	}

	@PostMapping(value = "/productoValoracion/actualizar")
	public void actualizarProductoValoracion(@RequestBody ProductoValoracion item) {
		productoValoracionService.actualizarProductosValoracion(item);
	}
}
