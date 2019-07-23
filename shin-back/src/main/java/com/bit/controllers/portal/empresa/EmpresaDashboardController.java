package com.bit.controllers.portal.empresa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.model.Usuario;

@Controller
@RequestMapping(value="/portal-empresa")
public class EmpresaDashboardController {
	
//	@Autowired
//	private UsuarioService usuarioService;
	
	@GetMapping(value="/dashboard")
	public String dasdboard(Model model, 
			@ModelAttribute("currentUser") Usuario currentUser) {
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
}
