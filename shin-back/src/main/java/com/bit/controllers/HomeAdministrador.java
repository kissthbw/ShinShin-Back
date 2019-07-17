package com.bit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.Usuario;
import com.bit.service.UsuarioService;

@Controller
@RequestMapping("/login")
public class HomeAdministrador {
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/administrador", method = RequestMethod.GET)
	public String redireccionaLogin(Model model) {
		
		model.addAttribute("item", new Usuario());
		
		return "login_administrador";
	}
	
	@RequestMapping(value = "/administrador", method = RequestMethod.POST)
	public String loginCuenta(@ModelAttribute Usuario item, BindingResult errors, Model model) {
		
		usuarioService.findUserByUserAndPassword(item);
		
		return "redirect:/catalogos/producto/list";
	}
}
