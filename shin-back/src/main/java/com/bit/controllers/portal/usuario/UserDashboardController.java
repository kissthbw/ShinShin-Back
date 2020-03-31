package com.bit.controllers.portal.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.model.MediosBonificacion;
import com.bit.model.Producto;
import com.bit.model.Usuario;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.UsuarioService;
import com.bit.service.UsuarioShingShingDetailService;

@Controller
@RequestMapping(value="/portal-usuario")
//@SessionAttributes("currentUser")
public class UserDashboardController {
	
	@Autowired
	private UsuarioService usuarioService;
	
//	@GetMapping(value="/")
//	public String home() {
//		return "login";
//	}
	
//	@GetMapping(value="/login")
//	public String loginUser() {
//		Usuario user = new Usuario();
//		user.setIdUsuario(2L);
//		
//		return "dashboard";
//	}
	
	@GetMapping(value="/dashboard")
	public String dasdboard(Model model) {
		//Se obtiene informacion del usuario logueado
		//1. Saldo total
		//2. Numero de tickets del mes
		//3. Numero de bonificaciones solicitadas
		//4. Cuentas registradas
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP rsp = usuarioService.obtieneInformacionGeneralUsuario(item);
			
			model.addAttribute("item", rsp);
		}
		
		
		
		return "usuario/user_dashboard";
	}
	
	@GetMapping(value="/dashboard/perfil")
	public String obtienePerfil(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			
			model.addAttribute("info", info);
			model.addAttribute("item", item);
		}
		
		
		return "usuario/perfil";
	}
	
	@GetMapping(value="/dashboard/tickets")
	public String obtieneTickets(Model model) {
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			ListItemsRSP rsp = usuarioService.obtieneTicketsPorUsuario(item);
			
			model.addAttribute("item", info);
			model.addAttribute("items", rsp.getTickets());
		}
		
		return "usuario/tickets";
	}
	
	@GetMapping(value="/dashboard/retiros")
	public String obtieneRetiros(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			ListItemsRSP rsp = usuarioService.obtienetHistoricosMediosBonificacionPorUsuario(item);
			
			model.addAttribute("item", info);
			model.addAttribute("items", rsp.getHistoricoMediosBonificaciones());
		}
		
		return "usuario/retiros";
	}
	
	@GetMapping(value="/dashboard/cuentas")
	public String obtieneCuentas(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			List<MediosBonificacion> tmp = new ArrayList<>();
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			InformacionUsuarioRSP rsp = usuarioService.obtenerMediosBonificacion(item);
			tmp.addAll( rsp.getMediosBonificacion().get(0).getList() );
			tmp.addAll( rsp.getMediosBonificacion().get(1).getList() );
			tmp.addAll( rsp.getMediosBonificacion().get(2).getList() );
			
			model.addAttribute("item", info);
			model.addAttribute("items", tmp);
		}
		
		
		return "usuario/cuentas";
	}
	
	/*
	 * SECCION DE RETIROS
	 */
	@GetMapping(value="/dashboard/retiro-bancario")
	public String getRetiroBancario(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			
			model.addAttribute("info", info);
			model.addAttribute("item", new Producto());
		}
		
		
		return "usuario/retiro-bancario";
	}
	
	@GetMapping(value="/dashboard/retiro-paypal")
	public String getRetiroPayPal(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			
			model.addAttribute("info", info);
			model.addAttribute("item", new Producto());
		}
		
		
		return "usuario/retiro-paypal";
	}
	
	@GetMapping(value="/dashboard/retiro-telefonico")
	public String getRetiroTelefonico(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			
			model.addAttribute("info", info);
			model.addAttribute("item", new Producto());
		}
		
		
		return "usuario/retiro-telefonico";
	}
	
	private UsuarioShingShingDetailService getAuthenticationUser() {
		UsuarioShingShingDetailService user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UsuarioShingShingDetailService) {
			user = (UsuarioShingShingDetailService) principal;
		}
		
		return user;
	}
}
