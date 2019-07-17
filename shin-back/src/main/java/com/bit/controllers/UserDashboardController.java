package com.bit.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.model.MediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.dto.response.MedioBonificacionUsuario;
import com.bit.service.UsuarioService;

@Controller
@RequestMapping(value="userDashdboard")
public class UserDashboardController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value="/")
	public String home() {
		return "login";
	}
	
	@GetMapping(value="/login")
	public String loginUser() {
		Usuario user = new Usuario();
		user.setIdUsuario(2L);
		
		//Se obtiene informacion del usuario logueado
		//1. Saldo total
		BigDecimal saldo = usuarioService.calculaCreditoTotal(user);
		
		//2. Numero de tickets del mes
		//3. Numero de bonificaciones solicitadas
		//4. Cuentas registradas
		
		return "dashboard";
	}
	
	@GetMapping(value="/dashboard")
	public String dasdboard(Model model) {
		Usuario item = new Usuario();
		item.setIdUsuario(2l);
		
		InformacionUsuarioRSP rsp = usuarioService.obtieneInformacionGeneralUsuario(item);
		
		model.addAttribute("item", rsp);
		
		return "user_dashboard";
	}
	
	@GetMapping(value="/tickets")
	public String obtieneTickets(Model model) {
		Usuario item = new Usuario();
		item.setIdUsuario(2l);
		
		InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
		ListItemsRSP rsp = usuarioService.obtieneTicketsPorUsuario(item);
		
		model.addAttribute("info", info);
		model.addAttribute("items", rsp.getTickets());
		
		return "tickets";
	}
	
	@GetMapping(value="/retiros")
	public String obtieneRetiros(Model model) {
		Usuario item = new Usuario();
		item.setIdUsuario(2l);
		
		InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
		ListItemsRSP rsp = usuarioService.obtienetHistoricosMediosBonificacionPorUsuario(item);
		
		model.addAttribute("info", info);
		model.addAttribute("items", rsp.getHistoricoMediosBonificaciones());
		
		return "retiros";
	}
	
	@GetMapping(value="/cuentas")
	public String obtieneCuentas(Model model) {
		Usuario item = new Usuario();
		item.setIdUsuario(2l);
		
		List<MediosBonificacion> tmp = new ArrayList<>();
		
		InformacionUsuarioRSP info = usuarioService.obtieneInformacionGeneralUsuario(item);
		InformacionUsuarioRSP rsp = usuarioService.obtenerMediosBonificacion(item);
		tmp.addAll( rsp.getMediosBonificacion().get(0).getList() );
		tmp.addAll( rsp.getMediosBonificacion().get(1).getList() );
		tmp.addAll( rsp.getMediosBonificacion().get(2).getList() );
		
		model.addAttribute("info", info);
		model.addAttribute("items", tmp);
		
		return "cuentas";
	}
}
