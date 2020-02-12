package com.bit.controllers.portal.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.CatalogoTienda;
import com.bit.service.CatalogoTiendaService;

@Controller
@RequestMapping("/catalogo_tienda")
public class CatalogoTiendaController {

	@Autowired
	private CatalogoTiendaService catalogoTiendaService;

	@RequestMapping(value = "tienda/save", method = RequestMethod.GET)
	public String redirectionMedio(Model model) {
		
		model.addAttribute("item", new CatalogoTienda());

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTienda());
		catalogoTiendaService.registrarTiendas(null, item);

		return "redirect:/tienda/save";
	}
}
