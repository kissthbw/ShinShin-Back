package com.bit.controllers.portal.usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.UsuarioService;

@Controller
//@RequestMapping("/")
public class RestaurarController {
	
	private static final Logger log= LoggerFactory.getLogger(RestaurarController.class);
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/restaurar/{id}", method = RequestMethod.GET)
	public String getRestaurar(Model model, @PathVariable String id) {
		
		log.info("Entrando en getRestaurar");
		Usuario item = new Usuario();
		item.setPassword_restore_link(id);
		
		SimpleResponse rsp = usuarioService.restaurarPasswordLink(item);
		
		if ( 200 == rsp.getCode() ) {
			model.addAttribute("item", new Usuario());
			return "restaurar";
		}
		else if ( 405 == rsp.getCode() ) {
			return "redirect:/405";
		}
		else {
			return "redirect:/405";
		}
		
		
	}
	
	@RequestMapping(value = "/405", method = RequestMethod.GET)
	public String get405(Model model) {
		return "405";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String get404(Model model) {
		return "404";
	}
	
	@RequestMapping(value = "/restaurar/{id}", method = RequestMethod.POST)
	public String postReataurar(@ModelAttribute Usuario item, @PathVariable String id) {
		
		log.info("Entrando en postReataurar");
		
		log.info("Usuario: {}, password actual: {}, password nuevo: {}", item.getCorreoElectronico(), item.getContrasenia(), item.getConfirmarContrasenia());
		SimpleResponse rsp = usuarioService.restaurarPassword(item);
//		SimpleResponse rsp = usuarioService.restaurarPassword(item);
		
		if ( 200 == rsp.getCode() ) {
			return "success";
			
		}
		else if ( 404 == rsp.getCode() ) {
			return "redirect:" + id + "?error=error";
		}
		else {
			return "404";
		}
		
	}
	
	@RequestMapping(value = "/restaurar", method = RequestMethod.POST)
	public String postReataurar2(@ModelAttribute Usuario item) {
		
		log.info("Entrando en postReataurar");
		
		log.info("Usuario: {}, password actual: {}, password nuevo: {}", item.getCorreoElectronico(), item.getContrasenia(), item.getConfirmarContrasenia());
		SimpleResponse rsp = usuarioService.restaurarPassword(item);
//		SimpleResponse rsp = usuarioService.restaurarPassword(item);
		
		if ( 200 == rsp.getCode() ) {
			return "redirect:/success";
		}
		else if ( 404 == rsp.getCode() ) {
			return "redirect:/404";
		}
		else {
			return "redirect:/404";
		}
		
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String getSuccess(Model model) {
		
		log.info("Entrando en getSuccess");
		return "success";
		
	}
}