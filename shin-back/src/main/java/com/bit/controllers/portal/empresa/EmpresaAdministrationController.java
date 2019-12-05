package com.bit.controllers.portal.empresa;

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
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.service.UsuarioService;
import com.bit.service.impl.UsuarioServiceImpl.Source;

@Controller
@RequestMapping("/portal-empresa")
public class EmpresaAdministrationController {
	
	private static final Logger log= LoggerFactory.getLogger(EmpresaAdministrationController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	/*
	 * Obtiene la pagina para capturar el correo solicitando restaurar password
	 */
	@RequestMapping(value = "/restaurar/solicitar", method = RequestMethod.GET)
	public String getRestaurarSolicitar(Model model) {
		
		log.info("Entrando en getRestaurar");
		model.addAttribute("item", new Usuario());
		return "empresa/restaurar_solicitar";

	}
	
	/*
	 * Procesa la peticion para solicitar link de restauracion de password
	 */
	@RequestMapping(value = "/restaurar/solicitar", method = RequestMethod.POST)
	public String postRestaurarSolicitar(Model model, @ModelAttribute Usuario item) {
		
		log.info("Entrando en getRestaurar");
		SimpleResponse rsp = usuarioService.solicitarRestaurarPassword(item);
		
		if( 200 == rsp.getCode() ) {
//			return "redirect:/portal-empresa";
			return "empresa/restaurar_confirmacion_envio";
		}
		else {
			return "redirect:?error=error";
		}
	}
	
	@RequestMapping(value = "/restaurar/captura/{id}", method = RequestMethod.GET)
	public String getRestaurarCaotura(Model model, @PathVariable String id) {
		
		log.info("Entrando en getRestaurar");
		Usuario item = new Usuario();
		item.setPassword_restore_link(id);
		
		SimpleResponse rsp = usuarioService.restaurarPasswordLink(item);
		
		if ( 200 == rsp.getCode() ) {
			model.addAttribute("item", new Usuario());
			return "empresa/restaurar_captura";
		}
		else if ( 405 == rsp.getCode() ) {
			return "redirect:/portal-empresa/restaurar/405";
		}
		else {
			return "redirect:/portal-empresa/restaurar/404";
		}

	}

//	@RequestMapping(value = "/restaurar/{id}", method = RequestMethod.GET)
//	public String getRestaurar(Model model, @PathVariable String id) {
//		
//		log.info("Entrando en getRestaurar");
//		Usuario item = new Usuario();
//		item.setPassword_restore_link(id);
//		
//		SimpleResponse rsp = usuarioService.restaurarPasswordLink(item);
//		
//		if ( 200 == rsp.getCode() ) {
//			model.addAttribute("item", new Usuario());
//			return "restaurar_style";
//		}
//		else if ( 405 == rsp.getCode() ) {
//			return "redirect:/405";
//		}
//		else {
//			return "redirect:/405";
//		}
//		
//		
//	}
	
	@RequestMapping(value = "/restaurar/405", method = RequestMethod.GET)
	public String get405(Model model) {
		return "empresa/405";
	}
	
	@RequestMapping(value = "/restaurar/404", method = RequestMethod.GET)
	public String get404(Model model) {
		return "empresa/404";
	}
	
	@RequestMapping(value = "/restaurar/captura/{id}", method = RequestMethod.POST)
	public String postReataurar(@ModelAttribute Usuario item, @PathVariable String id) {
		
		log.info("Entrando en postReataurar");
		item.setPassword_restore_link(id);
		log.info("Usuario: {}, password actual: {}, password nuevo: {}", item.getCorreoElectronico(), item.getContrasenia(), item.getConfirmarContrasenia());
		SimpleResponse rsp = usuarioService.restaurarPassword(item);
//		SimpleResponse rsp = usuarioService.restaurarPassword(item);
		
		if ( 200 == rsp.getCode() ) {
			return "empresa/restaurar_success";
			
		}
		else if ( 404 == rsp.getCode() ) {
			return "redirect:" + id + "?error=error";
		}
		else {
			return "404";
		}
		
	}
	
	@RequestMapping(value = "/restaurar/captura", method = RequestMethod.POST)
	public String postReataurar2(@ModelAttribute Usuario item) {
		
		log.info("Entrando en postReataurar");
		
		log.info("Usuario: {}, password actual: {}, password nuevo: {}", item.getCorreoElectronico(), item.getContrasenia(), item.getConfirmarContrasenia());
		SimpleResponse rsp = usuarioService.restaurarPassword(item);
//		SimpleResponse rsp = usuarioService.restaurarPassword(item);
		
		if ( 200 == rsp.getCode() ) {
			return "redirect:/empresa/success";
		}
		else if ( 404 == rsp.getCode() ) {
			return "redirect:/404";
		}
		else {
			return "redirect:/404";
		}
		
	}
	
	@RequestMapping(value = "restaurar/success", method = RequestMethod.GET)
	public String getSuccess(Model model) {
		
		log.info("Entrando en getSuccess");
		return "empresa/restaurar_success";
		
	}
	
	@RequestMapping(value = "restaurar/contacto", method = RequestMethod.GET)
	public String getContacto(Model model) {
		
		log.info("Entrando en getRestaurar");
		model.addAttribute("item", new Usuario());
		return "empresa/contacto";

	}
	
	@RequestMapping(value = "restaurar/crear-cuenta", method = RequestMethod.GET)
	public String getCrearCuenta(Model model) {
		
		log.info("Entrando en getRestaurar");
		model.addAttribute("item", new Usuario());
		return "empresa/crear_cuenta";

	}
	
	@RequestMapping(value = "restaurar/crear-cuenta", method = RequestMethod.POST)
	public String postCrearCuenta(Model model, @ModelAttribute Usuario item) {
		
		log.info("Entrando en postCrearCuenta");
		
		InformacionUsuarioRSP rsp = usuarioService.registrarUsuarios(item, Source.CONTROLLER);
		if ( 200 == rsp.getCode() ) {
			return "redirect:/portal-empresa/restaurar/validacion/" + rsp.getUsuario().getActivation_link();
		}
		
		return "empresa/restaurar_success";

	}
	
	@RequestMapping(value = "restaurar/validacion/{link}", method = RequestMethod.GET)
	public String getValidacion(Model model, @PathVariable String link) {
		
		
		Usuario item = new Usuario();
		item.setActivation_link(link);
		
		InformacionUsuarioRSP rsp = usuarioService.activationPasswordLink(item);
		
		if ( 200 == rsp.getCode() ) {
			String tel = rsp.getUsuario().getTelMovil().substring(10, rsp.getUsuario().getTelMovil().length());
			rsp.getUsuario().setTelMovil( "******" + tel );
			rsp.getUsuario().setIdUsuario( rsp.getId() );
			
			model.addAttribute("item", rsp.getUsuario());
			return "empresa/validacion";
//			return "restaurar_captura";
		}
		else if ( 405 == rsp.getCode() ) {
			return "redirect:/portal-empresa/restaurar/405";
		}
		else {
			return "redirect:/portal-empresa/restaurar/404";
		}
		
//		log.info("Entrando en getRestaurar");
//		Usuario u = new Usuario();
//		u.setTelMovil("5555");
//		model.addAttribute("item", u);
//		return "validacion";

	}
	
	@RequestMapping(value = "restaurar/validacion/{link}", method = RequestMethod.POST)
	public String postValidacion(Model model, @ModelAttribute Usuario item, @PathVariable String link) {
		
		log.info("Entrando a activarUsuarios para activar un usuario");
		SimpleResponse rsp = usuarioService.activarUsuarios(item, Source.CONTROLLER);
		return "empresa/validacion_success";

	}
	
	@RequestMapping(value = "restaurar/reenviar/{link}", method = RequestMethod.GET)
	public String getReenviar(Model model, @PathVariable String link) {
		
		log.info("Entrando a activarUsuarios para activar un usuario");
		Usuario item = new Usuario();
		item.setActivation_link( link );
		InformacionUsuarioRSP rsp = usuarioService.reenviarCodigoUsuario(item, Source.CONTROLLER);
		
		if( 200 == rsp.getCode() ) {
			return "redirect:/portal-empresa/restaurar/validacion/" + rsp.getUsuario().getActivation_link();
		}
		
		return "empresa/validacion_success";

	}
}