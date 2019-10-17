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

import com.bit.model.User;
import com.bit.model.Usuario;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.service.UsuarioService;

@Controller
@RequestMapping("/portal-usuario")
//@SessionAttributes("currentUser")
public class HomeUsuario {
	
	@ModelAttribute("currentUser")
	public User usuario() {
		return new User();
	}
	
	@GetMapping(value="")
	public String home() {
		return "redirect:/portal-usuario/userLogin";
	}
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String redireccionaLogin(Model model, @ModelAttribute("currentUser") User currentUser) {
		
		model.addAttribute("item", new User());
		
		return "login_usuario";
	}
	
	@RequestMapping(value = "/postUserLogin", method = RequestMethod.POST)
	public String loginCuenta(@ModelAttribute User item, 
			BindingResult errors, 
			Model model, 
//			@ModelAttribute("currentUser") User currentUser,
			RedirectAttributes attributes) {
		
		Usuario tmp = new Usuario();
		tmp.setUsuario( item.getUsername() );
		tmp.setContrasenia( item.getPassword() );
		
		InformacionUsuarioRSP rsp = usuarioService.findUserByUserAndPassword( tmp );

//		currentUser.setIdUsuario( rsp.getUsuario().getIdUsuario() );
//		attributes.addFlashAttribute("currentUser", currentUser);
		
		
//		return new RedirectView("dashboard");
		return "redirect:/portal-usuario/dashboard";
	}
}