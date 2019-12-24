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
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.service.UsuarioService;
import com.bit.service.impl.UsuarioServiceImpl.Source;

@Controller
@RequestMapping("/portal-usuario")
public class UsuarioAdministrationController {
	
	private static final Logger log= LoggerFactory.getLogger(UsuarioAdministrationController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	/*
	 * Obtiene la pagina para capturar el correo solicitando restaurar password
	 */
	@RequestMapping(value = "/restaurar/solicitar", method = RequestMethod.GET)
	public String getRestaurarSolicitar(Model model) {
		
		log.info("Entrando en getRestaurar");
		model.addAttribute("item", new Usuario());
		return "restaurar_solicitar";

	}
	
	/*
	 * Procesa la peticion para solicitar link de restauracion de password
	 */
	@RequestMapping(value = "/restaurar/solicitar", method = RequestMethod.POST)
	public String postRestaurarSolicitar(Model model, @ModelAttribute Usuario item) {
		
		log.info("Entrando en getRestaurar");
		SimpleResponse rsp = usuarioService.solicitarRestaurarPassword(item);
		
		if( 200 == rsp.getCode() ) {
//			return "redirect:/portal-usuario";
			return "restaurar_confirmacion_envio";
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
			return "restaurar_captura";
		}
		else if ( 405 == rsp.getCode() ) {
			return "redirect:/portal-usuario/restaurar/405";
		}
		else {
			return "redirect:/portal-usuario/restaurar/404";
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
		return "405";
	}
	
	@RequestMapping(value = "/restaurar/404", method = RequestMethod.GET)
	public String get404(Model model) {
		return "404";
	}
	
	@RequestMapping(value = "/restaurar/captura/{id}", method = RequestMethod.POST)
	public String postReataurar(@ModelAttribute Usuario item, @PathVariable String id) {
		
		log.info("Entrando en postReataurar");
		item.setPassword_restore_link(id);
		log.info("Usuario: {}, password actual: {}, password nuevo: {}", item.getCorreoElectronico(), item.getContrasenia(), item.getConfirmarContrasenia());
		SimpleResponse rsp = usuarioService.restaurarPassword(item);
//		SimpleResponse rsp = usuarioService.restaurarPassword(item);
		
		if ( 200 == rsp.getCode() ) {
			return "restaurar_success";
			
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
			return "redirect:/success";
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
		return "restaurar_success";
		
	}
	
	@RequestMapping(value = "restaurar/contacto", method = RequestMethod.GET)
	public String getContacto(Model model) {
		
		log.info("Entrando en getRestaurar");
		model.addAttribute("item", new Usuario());
		return "contacto";

	}
	
	@RequestMapping(value = "restaurar/crear-cuenta", method = RequestMethod.GET)
	public String getCrearCuenta(Model model) {
		
		log.info("Entrando en getRestaurar");
		model.addAttribute("item", new Usuario());
		return "crear_cuenta";

	}
	
	@RequestMapping(value = "restaurar/crear-cuenta", method = RequestMethod.POST)
	public String postCrearCuenta(Model model, @ModelAttribute Usuario item) {
		
		log.info("Entrando en postCrearCuenta");
		
		InformacionUsuarioRSP rsp = usuarioService.registrarUsuarios(item, Source.CONTROLLER);
		if ( 200 == rsp.getCode() ) {
			return "redirect:/portal-usuario/restaurar/validacion/" + rsp.getUsuario().getActivation_link();
		}
		
		return "restaurar_success";

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
			return "validacion";
//			return "restaurar_captura";
		}
		else if ( 405 == rsp.getCode() ) {
			return "redirect:/portal-usuario/restaurar/405";
		}
		else {
			return "redirect:/portal-usuario/restaurar/404";
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
		return "validacion_success";

	}
	
	@RequestMapping(value = "restaurar/reenviar/{link}", method = RequestMethod.GET)
	public String getReenviar(Model model, @PathVariable String link) {
		
		log.info("Entrando a activarUsuarios para activar un usuario");
		Usuario item = new Usuario();
		item.setActivation_link( link );
		InformacionUsuarioRSP rsp = usuarioService.reenviarCodigoUsuario(item, Source.CONTROLLER);
		
		if( 200 == rsp.getCode() ) {
			return "redirect:/portal-usuario/restaurar/validacion/" + rsp.getUsuario().getActivation_link();
		}
		
		return "validacion_success";

	}
	
	@RequestMapping(value = "restaurar/bonificaciones-general", method = RequestMethod.GET)
	public String getObtenerGeneral(Model model) {
		
		log.info("Entrando a getObtenerGeneral");
		Usuario item = new Usuario();
		
		return "administrador/bonificaciones-general";
	}
	
	@RequestMapping(value = "restaurar/bonificaciones-depositos", method = RequestMethod.GET)
	public String getObtenerDepositos(Model model) {
		
		log.info("Entrando a getObtenerDeporistos");
		Usuario item = new Usuario();
		
		return "administrador/bonificaciones-depositos";
	}
	
	@RequestMapping(value = "restaurar/bonificaciones-depositos-detalle", method = RequestMethod.GET)
	public String getObtenerDepositosDetalle(Model model) {
		
		log.info("Entrando a getObtenerDeporistosDetalle");
		Usuario item = new Usuario();
		
		return "administrador/bonificaciones-depositos-detalle";
	}
	
	@RequestMapping(value = "restaurar/bonificaciones-recargas", method = RequestMethod.GET)
	public String getObtenerRecargas(Model model) {
		
		log.info("Entrando a getObtenerRecargas");
		Usuario item = new Usuario();
		
		return "administrador/bonificaciones-recargas";
	}
	
	@RequestMapping(value = "restaurar/bonificaciones-recargas-detalle", method = RequestMethod.GET)
	public String getObtenerRecargasDetalle(Model model) {
		
		log.info("Entrando a getObtenerRecargasDetalle");
		Usuario item = new Usuario();
		
		return "administrador/bonificaciones-recargas-detalle";
	}
	
	@RequestMapping(value = "restaurar/estadisticas-general", method = RequestMethod.GET)
	public String getObtenerEstadisticasGeneral(Model model) {
		
		log.info("Entrando a getObtenerEstadisticasGeneral");
		Usuario item = new Usuario();
		
		return "administrador/estadisticas-general";
	}
	
	@RequestMapping(value = "restaurar/estadisticas-marcas", method = RequestMethod.GET)
	public String getObtenerEstadisticasMarcas(Model model) {
		
		log.info("Entrando a getObtenerEstadisticasMarcas");
		Usuario item = new Usuario();
		
		return "administrador/estadisticas-marcas";
	}
	
	@RequestMapping(value = "restaurar/estadisticas-tickets", method = RequestMethod.GET)
	public String getObtenerEstadisticasTickets(Model model) {
		
		log.info("Entrando a getObtenerEstadisticasTickets");
		Usuario item = new Usuario();
		
		return "administrador/estadisticas-tickets";
	}
	
	@RequestMapping(value = "restaurar/estadisticas-tickets-detalle", method = RequestMethod.GET)
	public String getObtenerEstadisticasTicketsDetalle(Model model) {
		
		log.info("Entrando a getObtenerEstadisticasTicketsDetalle");
		Usuario item = new Usuario();
		
		return "administrador/estadisticas-tickets-detalle";
	}
	
	@RequestMapping(value = "restaurar/estadisticas-tickets-detalle-segundoDetalle", method = RequestMethod.GET)
	public String getObtenerEstadisticasTicketsDetalleSegundoDetalle(Model model) {
		
		log.info("Entrando a getObtenerEstadisticasTicketsDetalleSegundoDetalle");
		Usuario item = new Usuario();
		
		return "administrador/estadisticas-tickets-detalle-segundoDetalle";
	}
	
	@RequestMapping(value = "restaurar/estadisticas-usuarios", method = RequestMethod.GET)
	public String getObtenerEstadisticasUsuarios(Model model) {
		
		log.info("Entrando a getObtenerEstadisticasUsuarios");
		Usuario item = new Usuario();
		
		return "administrador/estadisticas-usuarios";
	}
	
	@RequestMapping(value = "restaurar/lista-marcas-editar", method = RequestMethod.GET)
	public String getObtenerMarcasEditar(Model model) {
		
		log.info("Entrando a getObtenerMarcasEditar");
		Usuario item = new Usuario();
		
		return "administrador/lista_marcas-editar";
	}
	
	@RequestMapping(value = "restaurar/lista-marcas-nuevo", method = RequestMethod.GET)
	public String getObtenerMarcasNuevo(Model model) {
		
		log.info("Entrando a getObtenerMarcasNuevo");
		Usuario item = new Usuario();
		
		return "administrador/lista_marcas-nuevo";
	}
	
	@RequestMapping(value = "restaurar/lista-marcas", method = RequestMethod.GET)
	public String getObtenerMarcas(Model model) {
		
		log.info("Entrando a getObtenerMarcas");
		Usuario item = new Usuario();
		
		return "administrador/lista_marcas";
	}
	
	@RequestMapping(value = "restaurar/usuario-detalle", method = RequestMethod.GET)
	public String getObtenerUsuarioDetalle(Model model) {
		
		log.info("Entrando a getObtenerUsuarioDetalle");
		Usuario item = new Usuario();
		
		return "administrador/usuario-detalle";
	}
}