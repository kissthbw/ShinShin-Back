package com.bit.controllers.portal.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.Contacto;
import com.bit.service.UsuarioService;

@Controller
//@RequestMapping("/")
public class ContactoController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/contacto", method = RequestMethod.GET)
	public String redireccionaLogin( Model model ) {
		model.addAttribute("item", new Contacto());
		return "contacto";
	}
	
	@RequestMapping(value = "/contacto", method = RequestMethod.POST)
	public String postCatDepartamento(@ModelAttribute Contacto item,
			BindingResult errors, Model model) {

		usuarioService.registraContacto(item);

		return "redirect:/";
	}
}