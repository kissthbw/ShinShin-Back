package com.bit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.CatalogoTipoProducto;
import com.bit.service.CatalogoTipoProductoService;

@Controller
@RequestMapping("/catalogo_tipo_producto")
public class CatalogoTipoProductoController {

	@Autowired
	private CatalogoTipoProductoService catalogoTipoProductoService;

	@RequestMapping(value = "/tipoProducto/save", method = RequestMethod.GET)
	public String redireccionaMedio(Model model) {

		model.addAttribute("item", new CatalogoTipoProducto());

		return "catalogo_tipo_producto";
	}

	@RequestMapping(value = "/tipoProducto/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTipoProducto());
		catalogoTipoProductoService.registrarCatalogoTipoProductos(item);

		return "redirect:/tipoProducto/save";
	}
}
