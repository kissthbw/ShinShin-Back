package com.bit.controllers.portal.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.CatalogoMediosBonificacion;
import com.bit.service.CatalogoMediosBonificacionService;

@Controller
@RequestMapping("/catalogo_medios_bonificacion")
public class CatalogoMediosBonificacionController {

	@Autowired
	private CatalogoMediosBonificacionService catalogoMediosBonificacionService;

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.GET)
	public String redireccionaMedio(Model model) {

		model.addAttribute("item", new CatalogoMediosBonificacion());

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {

		System.out.println(item.getNombreMedioBonificacion());
		catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);

		return "redirect:/medioBonificacion/save";
	}
}
