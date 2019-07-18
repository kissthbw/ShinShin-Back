package com.bit.controllers.portal.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.bit.model.Usuario;
import com.bit.service.UsuarioService;

@Controller
@RequestMapping("/portal-administrador")
@SessionAttributes("currentAdmin")
public class HomeAdministrador {
	
	@Autowired
	private UsuarioService usuarioService;

	@ModelAttribute("currentAdmin")
	public Usuario usuario() {
		return new Usuario();
	}
	
	@GetMapping(value="")
	public String home() {
		return "redirect:/portal-administrador/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String redireccionaLogin(Model model, @ModelAttribute("currentAdmin") Usuario currentUser) {
		
		model.addAttribute("item", new Usuario());
		
		return "login_administrador";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView loginCuenta(@ModelAttribute Usuario item, 
			BindingResult errors, 
			Model model,
			@ModelAttribute("currentAdmin") Usuario currentUser,
			RedirectAttributes attributes) {
		
		usuarioService.findUserByUserAndPassword(item);
		
		return new RedirectView("producto/list");
		
//		return "redirect:/portal-administrador/producto/list";
	}
}
