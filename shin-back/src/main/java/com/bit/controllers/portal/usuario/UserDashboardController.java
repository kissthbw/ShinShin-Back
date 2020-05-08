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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.common.Utils;
import com.bit.controllers.portal.administrador.CatalogosController;
import com.bit.model.CatalogoSexo;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.HistoricoMediosBonificacionService;
import com.bit.service.UsuarioService;
import com.bit.service.UsuarioShingShingDetailService;
import com.bit.service.impl.UsuarioServiceImpl.Source;
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

	
	@GetMapping(value="/dashboard")
	public String dasdboard(Model model) {
		//Se obtiene informacion del usuario logueado
		//1. Saldo total
		//2. Historico de tickets
		//3. Historico de movimientos
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP rsp = usuarioService.obtieneInformacionGeneralUsuario(item);
			ListItemsRSP historicoMovimientos = usuarioService.obtienetHistoricosMediosBonificacionPorUsuario(item);
			ListItemsRSP historicoTickets = usuarioService.obtieneTicketsPorUsuario(item);
			
			model.addAttribute("info", rsp);
			model.addAttribute("movimientos", historicoMovimientos.getHistoricoMediosBonificaciones());
			model.addAttribute("tickets", historicoTickets.getTickets());
		}
		
		
		
		return "usuario/user_dashboard";
	}
	
	@GetMapping(value="/dashboard/perfil")
	public String obtienePerfil(Model model) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
//			Usuario item = new Usuario();
//			item.setIdUsuario( current.getUsuario().getIdUsuario() );

			
			List<CatalogoSexo> sexos = usuarioService.obtieneCatalogoSexo();
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario( current.getUsuario() );
			//Depurar el telefono y quitar el +521
			String movil = info.getUsuario().getTelMovil().replace("+521", "");
			info.getUsuario().setContrasenia(null);
			info.getUsuario().setConfirmarContrasenia(null);
			info.getUsuario().setContraseniaActual(null);
			info.getUsuario().setTelMovil( movil );
			
			model.addAttribute("dias", Utils.obtieneDias());
			model.addAttribute("meses", Utils.obtieneMeses());
			model.addAttribute("anios", Utils.obtieneAnios());
			
			model.addAttribute("sexos", sexos);
			model.addAttribute("info", info);
			model.addAttribute("item", info.getUsuario());
		}
		
		
		return "usuario/perfil";
	}
	
//	public String postCatDepartamento(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item,
//			BindingResult errors, Model model) {
//
//		log.info("Entrando en postCatDepartamento");
//		catalogoTipoProductoService.registrarCatalogoTipoProductos(file, item);
//
//		log.info("Saliendo de postCatDepartamento");
//
//		return "redirect:/portal-administrador/departamento/list?id=1&type=1";
//	}
	
	@PostMapping(value="/dashboard/perfil")
	public String actualizaPerfil(Model model, @ModelAttribute Usuario item, BindingResult errors) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
//			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			if( null != item.getTelMovil() && item.getTelMovil().length() == 10 ) {
				item.setTelMovil( "+521" + item.getTelMovil() );
			}

			
			log.info("Entrando a actualizarUsuarios para modificar uno o varios valores de usuario");
			InformacionUsuarioRSP rsp = usuarioService.actualizarUsuarios(item, Source.CONTROLLER);
		}
		
		
		return "redirect:/portal-usuario/dashboard";
	}
	
	@GetMapping(value="/dashboard/tickets")
	public String obtieneTickets(Model model) {
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			ListItemsRSP rsp = usuarioService.obtieneTicketsPorUsuario(item);
			
			model.addAttribute("info", info);
			model.addAttribute("items", rsp.getTickets());
		}
		
		return "usuario/tickets";
	}
	
	@GetMapping(value="/dashboard/retiros")
	public String obtieneRetiros(Model model, @RequestParam(required = false) String option){
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
			ListItemsRSP rsp = usuarioService.obtienetHistoricosMediosBonificacionPorUsuario(item);
			
			InformacionUsuarioRSP infoWithMedios = usuarioService.obtenerMediosBonificacion(item);
			
			List<MediosBonificacion> tmp = new ArrayList<>();
			tmp.addAll( infoWithMedios.getMediosBonificacion().get(0).getList() );
			tmp.addAll( infoWithMedios.getMediosBonificacion().get(1).getList() );
			tmp.addAll( infoWithMedios.getMediosBonificacion().get(2).getList() );
			
			
			List<String> bancos = new ArrayList<>();
			bancos.add("Afirme");
			bancos.add("Banbajio");
			bancos.add("Banca Mifiel");
			bancos.add("Banco Azteca");
			bancos.add("BanCoppel");
			bancos.add("Banorte");
			bancos.add("Banregio");
			bancos.add("BBVA");
			bancos.add("CityBanamex");
			bancos.add("HSBC");
			bancos.add("Inbursa");
			bancos.add("IXE");
			bancos.add("Multiva");
			bancos.add("Santander");
			bancos.add("Scottiabank");
			
			List<String> com = new ArrayList<>();
			com.add("AT&T");
			com.add("Movistar");
			com.add("Telcel");
			com.add("Unefon");
			com.add("Virgin Mobile");
			
			infoWithMedios.getMediosBonificacion();
			model.addAttribute("cuentasB", infoWithMedios.getMediosBonificacion().get(0));
			model.addAttribute("cuentasP", infoWithMedios.getMediosBonificacion().get(1));
			model.addAttribute("telefonos", infoWithMedios.getMediosBonificacion().get(2));
			model.addAttribute("bancos", bancos);
			model.addAttribute("com", com);
			model.addAttribute("item", new HistoricoMediosBonificacion());
			model.addAttribute("cuenta", new MediosBonificacion());
			
			if ( null == option) {
				model.addAttribute("active1", "active");
				model.addAttribute("active2", "");
				model.addAttribute("active3", "");
			}
			else {
				switch (option) {
				case "1":
					model.addAttribute("active1", "active");
					model.addAttribute("active2", "");
					model.addAttribute("active3", "");
					break;
				case "2":
					model.addAttribute("active1", "");
					model.addAttribute("active2", "active");
					model.addAttribute("active3", "");
					break;
				case "3":
					model.addAttribute("active1", "");
					model.addAttribute("active2", "");
					model.addAttribute("active3", "active");
					break;

				default:
					model.addAttribute("active1", "active");
					model.addAttribute("active2", "");
					model.addAttribute("active3", "");
					break;
				}
			}
			
			model.addAttribute("info", info);
			
			//Obtiene lista de bonificaciones
			//model.addAttribute("items", rsp.getHistoricoMediosBonificaciones());
			model.addAttribute("items", tmp);
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
			
			model.addAttribute("info", info);
			model.addAttribute("items", tmp);
		}
		
		
		return "usuario/cuentas";
	}
	
	//TODO: SECCION DE ALTA DE CUENTAS no esta contemplada, pero por si acaso
	/*
	 * SECCION DE ALTA DE CUENTAS
	 * BANOS
	 * @PostMapping(value = "/mediosBonificacion/guardar")
	public SimpleResponse guardarMediosBonificacion(@RequestBody MediosBonificacion item) {
		
		log.info("Entrando a guardarMediosBonificacion");
		SimpleResponse rsp= mediosBonificacionService.guardarMediosBonificacion(item);
		
		return rsp;
	}
	 */
	@PostMapping(value="/dashboard/agregar-medioBonificacion")
	public @ResponseBody String getSaveBanco(Model model, @ModelAttribute MediosBonificacion item) {
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario u = new Usuario();
			u.setIdUsuario( current.getUsuario().getIdUsuario() );
			
			log.info( "Guardando medio a usuario: {}", u.getIdUsuario() );
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
	
	@CrossOrigin
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
