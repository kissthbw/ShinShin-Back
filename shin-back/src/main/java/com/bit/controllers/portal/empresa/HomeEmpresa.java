package com.bit.controllers.portal.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.bit.model.User;
import com.bit.model.Usuario;
import com.bit.service.UsuarioService;

@Controller
@RequestMapping("/portal-empresa")
public class HomeEmpresa {
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/empresaLogin", method = RequestMethod.GET)
	public String redireccionaLogin( 
			@RequestParam(value = "error", required = false) String error, 
            @RequestParam(value = "logout", required = false) String logout,
            @ModelAttribute("currentAdmin") User currentUser,
            Model model) {
		
		model.addAttribute("item", new User());
		
		return "empresa/login_empresa";
	}
	
	@RequestMapping(value = "/postEmpresaLogin", method = RequestMethod.POST)
	public RedirectView loginCuenta(@ModelAttribute User item, 
			BindingResult errors, 
			Model model,
			RedirectAttributes attributes) {
		
//		usuarioService.findUserByUserAndPassword(item);
		
		return new RedirectView("producto/list");
		
//		return "redirect:/portal-administrador/producto/list";
	}
	
	@RequestMapping(value = "/empresa", method = RequestMethod.POST)
	public String loginCuenta(@ModelAttribute Usuario item, BindingResult errors, Model model) {
		
		usuarioService.findUserByUserAndPassword(item);
		
		return "redirect:/catalogos/producto/list";
	}
}