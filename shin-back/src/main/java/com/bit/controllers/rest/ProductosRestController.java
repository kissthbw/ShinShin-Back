package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductosRestController {

	@Autowired
	private ProductoService productoService;

	/*
	 * @ResponseBody, esta anotacion da la capacidad de regresar una respuesta de
	 * acuerdo a la configuración del recurso. En nuestro caso como la libreria
	 * jackson-databind esta disponible (ver pom.xml) se formatea en automatico a
	 * JSON
	 * 
	 */

	/**
	 * Recurso REST para obtener una lista de productos.
	 */
	@GetMapping(value = "/list")
	public @ResponseBody List<Producto> getProductos() {
		System.out.println("Get Productos");
		List<Producto> list = productoService.getProductos();

		return list;
	}

	/*
	 * @RequestBody, esta anotacion habilita la recepcion de mensajes segun el tipo
	 * de mensaje que se haya configurado, en este caso se recibe un mensaje JSON
	 * Por default detecta el formato JSON debido a la libreria jackson-databind
	 * esta disponible (ver pom.xml)
	 */

	/**
	 * 
	 * @param item
	 */
	@PostMapping(value = "/producto/registrar")
	public @ResponseBody SimpleResponse registrarProducto(@RequestBody Producto item) {
		SimpleResponse rsp = productoService.registrarProductos(item);
		
		return rsp;
	}

	@PostMapping(value = "/producto/actualizar")
	public @ResponseBody SimpleResponse actualizarProducto(@RequestBody Producto item) {
		SimpleResponse rsp = productoService.actualizarProductos(item);
		
		return rsp;
	}
}
