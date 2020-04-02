package com.bit.controllers.portal.usuario;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.controllers.portal.administrador.CatalogosController;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.HistoricoMediosBonificacionService;
import com.bit.service.UsuarioService;
import com.bit.service.UsuarioShingShingDetailService;
import com.ibm.icu.util.Calendar;

@Controller
@RequestMapping(value="/portal-usuario")
//@SessionAttributes("currentUser")
public class UserDashboardController {
	
	private static Logger log = LoggerFactory.getLogger( CatalogosController.class );
	
	private static String[] montos = {"10", "20", "30", "50", "100", "200", "500"};
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private HistoricoMediosBonificacionService historicoMediosBonificacionService;
	
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
	
	//TODO: SECCION DE ALTA DE CUENTAS no esta contemplada, pero por si acaso
	/*
	 * SECCION DE ALTA DE CUENTAS
	 * BANOS
	 */
	@GetMapping(value="/dashboard/save-banco")
	public String getSaveBanco(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(item);
			
			infoWithMedios.getMediosBonificacion();
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(0));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-bancario";
	}
	
	/*
	 * PAYPAL
	 */
	@GetMapping(value="/dashboard/save-paypal")
	public String getSavePayPal(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(item);
			
			infoWithMedios.getMediosBonificacion();
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(0));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-bancario";
	}
	
	/*
	 * TELEFONO
	 */
	@GetMapping(value="/dashboard/save-telefono")
	public String getSaveTelefono(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(item);
			
			infoWithMedios.getMediosBonificacion();
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(0));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-bancario";
	}
	
	
	/*
	 * SECCION DE RETIROS
	 * SECCION DE RETIROS - RETIRO BANCARIO 
	 */
	@GetMapping(value="/dashboard/retiro-bancario")
	public String getRetiroBancario(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(item);
			
			infoWithMedios.getMediosBonificacion();
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(0));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-bancario";
	}
	
	@PostMapping(value="/dashboard/retiro-bancario")
	public String postRetiroBancario( @ModelAttribute HistoricoMediosBonificacion item, BindingResult errors, Model model ) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();
		
		if ( null != current ) {

			Calendar c = Calendar.getInstance();
			item.setFechaBonificacion( c.getTime() );
			item.setHoraBonificacion( c.getTime() );
			item.setUsuario( current.getUsuario() );
			
			rsp = historicoMediosBonificacionService.registrarHistoricosMediosBonificacion(item);
			
			Usuario u = new Usuario();
			u.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(u);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(u);
			
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(0));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-bancario";
	}
	
	/*
	 * SECCION DE RETIROS - RETIRO PAYPAL 
	 */
	@GetMapping(value="/dashboard/retiro-paypal")
	public String getRetiroPayPal(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(item);
			
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(1));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-paypal";
	}
	
	@PostMapping(value="/dashboard/retiro-paypal")
	public String postRetiroPayPal(@ModelAttribute HistoricoMediosBonificacion item, BindingResult errors, Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();
		
		if ( null != current ) {

			Calendar c = Calendar.getInstance();
			item.setFechaBonificacion( c.getTime() );
			item.setHoraBonificacion( c.getTime() );
			item.setUsuario( current.getUsuario() );
			
			rsp = historicoMediosBonificacionService.registrarHistoricosMediosBonificacion(item);
			
			Usuario u = new Usuario();
			u.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(u);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(u);
			
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(1));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-paypal";
	}
	
	/*
	 * SECCION DE RETIROS - RETIRO TELEFONICO 
	 */
	@GetMapping(value="/dashboard/retiro-telefonico")
	public String getRetiroTelefonico(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(item);
			
			model.addAttribute("info", info);
			model.addAttribute("montos", montos);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(2));
			model.addAttribute("item", new HistoricoMediosBonificacion());
		}
		
		
		return "usuario/retiro-telefonico";
	}
	
	@PostMapping(value="/dashboard/retiro-telefonico")
	public String postRetiroTelefonico(@ModelAttribute HistoricoMediosBonificacion item, BindingResult errors, Model model) {
		
		log.info("Entrando a guardarHistoricosMediosBonificacion");
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			
			Calendar c = Calendar.getInstance();
			item.setFechaBonificacion( c.getTime() );
			item.setHoraBonificacion( c.getTime() );
			item.setUsuario( current.getUsuario() );
			
			rsp = historicoMediosBonificacionService.registrarHistoricosMediosBonificacion(item);
			
			Usuario u = new Usuario();
			u.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(u);
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(u);
			
			model.addAttribute("info", info);
			model.addAttribute("medios", infoWithMedios.getMediosBonificacion().get(2));
			model.addAttribute("item", new HistoricoMediosBonificacion());
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
