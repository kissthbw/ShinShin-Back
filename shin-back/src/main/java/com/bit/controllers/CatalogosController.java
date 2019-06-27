package com.bit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.CatalogoTipoProducto;

@Controller
@RequestMapping("/catalogos")
public class CatalogosController {

	@RequestMapping(value = "/departamento/save", method = RequestMethod.GET)
	public String redireccionaCatDepartamentos(Model model) {

		model.addAttribute("item", new CatalogoTipoProducto());

		return "cat_departamentos";
	}
	
	@RequestMapping(value = "/departamento/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTipoProducto());
//		catalogoTipoProductoService.registrarCatalogoTipoProductos(item);

		return "redirect:/catalogos/departamento/save";
	}
}
