package com.bit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.CatalogoMarca;
import com.bit.service.CatalogoMarcaService;

@Controller
@RequestMapping("/catalogo_marca")
public class CatalogoMarcaController {

	@Autowired
	private CatalogoMarcaService catalogoMarcaService;

	@RequestMapping(value = "/marca/save", method = RequestMethod.GET)
	public String redireccionaMedio(Model model) {

		model.addAttribute("item", new CatalogoMarca());

		return "catalogo_marca";
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoMarca item, BindingResult errors, Model model) {

		System.out.println(item.getNombreMarca());
		catalogoMarcaService.registrarMarcas(item);

		return "redirect:/marca/save";
	}	
}
