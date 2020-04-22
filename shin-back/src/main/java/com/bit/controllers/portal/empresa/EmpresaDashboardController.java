package com.bit.controllers.portal.empresa;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.model.Usuario;
import com.bit.service.ProveedorDetailService;

@Controller
@RequestMapping(value="/portal-empresa")
public class EmpresaDashboardController {
	
//	@Autowired
//	private UsuarioService usuarioService;
	
	@GetMapping(value="/dashboard")
	public String dasdboard(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		ProveedorDetailService current = getAuthenticationUser();
		
		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}
		
		//Se obtiene informacion del usuario logueado
		//1. Saldo total
		//2. Numero de tickets del mes
		//3. Numero de bonificaciones solicitadas
		//4. Cuentas registradas
		
//		Usuario item = new Usuario();
//		item.setIdUsuario( currentUser.getIdUsuario() );
//		
//		InformacionUsuarioRSP rsp = usuarioService.obtieneInformacionGeneralUsuario(item);
//		
//		model.addAttribute("item", rsp);
		
		return "empresa_dashboard";
	}
	@GetMapping(value="/productos")
	public String productos(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		
//		Usuario item = new Usuario();
//		item.setIdUsuario( currentUser.getIdUsuario() );
//		
//		InformacionUsuarioRSP rsp = usuarioService.obtieneInformacionGeneralUsuario(item);
//		
//		model.addAttribute("item", rsp);
		
		return "productos_empresa";
	}
	@GetMapping(value="/finanzas")
	public String finanzas(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		
//		Usuario item = new Usuario();
//		item.setIdUsuario( currentUser.getIdUsuario() );
//		
//		InformacionUsuarioRSP rsp = usuarioService.obtieneInformacionGeneralUsuario(item);
//		
//		model.addAttribute("item", rsp);
		
		return "empresa_finanzas";
	}
	
	@GetMapping(value="/usuarios")
	public String usuarios(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
		
		
//		Usuario item = new Usuario();
//		item.setIdUsuario( currentUser.getIdUsuario() );
//		
//		InformacionUsuarioRSP rsp = usuarioService.obtieneInformacionGeneralUsuario(item);
//		
//		model.addAttribute("item", rsp);
		
		return "empresa_usuarios";
	}
	
	private ProveedorDetailService getAuthenticationUser() {
		ProveedorDetailService user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof ProveedorDetailService) {
			user = (ProveedorDetailService) principal;
		}

		return user;
	}
}
