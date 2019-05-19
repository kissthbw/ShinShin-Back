package com.bit.controllers.rest;

import java.util.List;

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

	@Autowired
	private CatalogoTipoProductoService catalogoTipoProductoService;

	@GetMapping(value = "/list")
	public @ResponseBody List<CatalogoTipoProducto> getCatalogoTipoProductos() {
		System.out.println("Get CatalogoTipoProductos");
		List<CatalogoTipoProducto> list = catalogoTipoProductoService.getCatalogoTipoProductos();

		return list;
	}

	@PostMapping(value = "/catalogoTipoProductos/registrar")
	public void registrarTipoProductos(@RequestBody CatalogoTipoProducto item) {
		catalogoTipoProductoService.registrarCatalogoTipoProductos(item);
	}

	@PostMapping(value = "/catalogoTipoProductos/actualizar")
	public void actualizarTipoProductos(@RequestBody CatalogoTipoProducto item) {
		catalogoTipoProductoService.actualizarCatalogoTipoProductos(item);
	}
}
