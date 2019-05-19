package com.bit.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.TipoProducto;
import com.bit.service.TipoProductoService;

@RestController
@RequestMapping("/tipoProductos")
public class TipoProductoRestController {

	@Autowired
	private TipoProductoService tipoProductoService;

	@GetMapping(value = "/list")
	public @ResponseBody List<TipoProducto> getTipoProductos() {
		System.out.println("Get TipoProductos");
		List<TipoProducto> list = tipoProductoService.getTipoProductos();

		return list;
	}

	@PostMapping(value = "/tipoProductos/guardar")
	public void guardarTipoProductos(@RequestBody TipoProducto item) {
		tipoProductoService.guardarTipoProductos(item);
	}

	@PostMapping(value = "/tipoProductos/actualizar")
	public void actualizarTipoProductos(@RequestBody TipoProducto item) {
		tipoProductoService.actualizarTipoProductos(item);
	}
}
