package com.bit.controllers.portal.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.bit.model.User;
import com.bit.service.UsuarioService;

@Controller
@RequestMapping("/portal-administrador")
@SessionAttributes("currentAdmin")
public class HomeAdministrador {
	
	@Autowired
	private UsuarioService usuarioService;

	@ModelAttribute("currentAdmin")
	public User usuario() {
		return new User();
	}
	
	@GetMapping(value="")
	public String home() {
		return "redirect:/portal-administrador/adminLogin";
	}
	
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String redireccionaLogin(Model model, 
			@ModelAttribute("currentAdmin") User currentUser,
			@RequestParam(value = "error", required = false) String error, 
            @RequestParam(value = "logout", required = false) String logout) {
		
		model.addAttribute("item", new User());
		
		return "login_administrador";
	}
	
	@RequestMapping(value = "/postAdminLogin", method = RequestMethod.POST)
	public RedirectView loginCuenta(@ModelAttribute User item, 
			BindingResult errors, 
			Model model,
			RedirectAttributes attributes) {
		
//		usuarioService.findUserByUserAndPassword(item);
		
		return new RedirectView("producto/list");
		
//		return "redirect:/portal-administrador/producto/list";
	}
}
