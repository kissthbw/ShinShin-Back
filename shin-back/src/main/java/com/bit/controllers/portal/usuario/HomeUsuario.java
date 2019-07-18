package com.bit.controllers.portal.usuario;

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
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.service.UsuarioService;

@Controller
@RequestMapping("/portal-usuario")
@SessionAttributes("currentUser")
public class HomeUsuario {
	
	@ModelAttribute("currentUser")
	public Usuario usuario() {
		return new Usuario();
	}
	
	@GetMapping(value="")
	public String home() {
		return "redirect:/portal-usuario/login";
	}
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String redireccionaLogin(Model model, @ModelAttribute("currentUser") Usuario currentUser) {
		
		model.addAttribute("item", new Usuario());
		
		return "login_usuario";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView loginCuenta(@ModelAttribute Usuario item, 
			BindingResult errors, 
			Model model, 
			@ModelAttribute("currentUser") Usuario currentUser,
			RedirectAttributes attributes) {
		
		InformacionUsuarioRSP rsp = usuarioService.findUserByUserAndPassword(item);
		currentUser = rsp.getUsuario();
		currentUser.setIdUsuario( rsp.getUsuario().getIdUsuario() );
		attributes.addFlashAttribute("currentUser", currentUser);
		
		
		return new RedirectView("dashboard");
	}
}