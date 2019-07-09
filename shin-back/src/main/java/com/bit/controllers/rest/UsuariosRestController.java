package com.bit.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosRestController { 
	
	private static final Logger log= LoggerFactory.getLogger(UsuariosRestController.class);

	//autowired inyecta (crea) de tipo usuarioservice
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getUsuarios() {
		
		log.info("Entrando a getUsuarios");
		ListItemsRSP rsp = usuarioService.getUsuarios();

		return rsp;
	}

	@PostMapping(value = "/usuario/registrar")
	public @ResponseBody SimpleResponse registrarUsuario(@RequestBody Usuario item) {
		
		log.info("Entrando a registrarUsuarios para registrar un nuevo usuario");
		//Tel movil: formato +5215534714616
		SimpleResponse rsp = usuarioService.registrarUsuarios(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/usuario/activar")
	public @ResponseBody SimpleResponse activarUsuario(@RequestBody Usuario item) {
		
		log.info("Entrando a activarUsuarios para activar un usuario");
		SimpleResponse rsp = usuarioService.activarUsuarios(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/usuario/actualizar")
	public @ResponseBody SimpleResponse actualizarUsuario(@RequestBody Usuario item) {
		
		log.info("Entrando a actualizarUsuarios para modificar uno o varios valores de usuario");
		SimpleResponse rsp = usuarioService.actualizarUsuarios(item);
		
		return rsp;
	}
	
	@PostMapping(value = "/usuario/login")
	public @ResponseBody Usuario findUserByUser(@RequestBody Usuario item) {
		
		log.info("Entrando a findUserByUser");
		Usuario user = usuarioService.findUserByUser(item);
		
		return user;
	}
	
	@PostMapping(value = "/usuario/login2")
	public @ResponseBody InformacionUsuarioRSP findUserByUserAndPassword(@RequestBody Usuario item) {
		
		log.info("Rntrando a findUserByUserAndPassword");
		InformacionUsuarioRSP user = usuarioService.findUserByUserAndPassword(item);
		
		return user;
	}
	
	@PostMapping(value = "/usuario/registrar/ticket")
	public @ResponseBody InformacionUsuarioRSP registrarTicketUsuario(@RequestBody Usuario item) {
		log.info("Entrando en registrarTicketUsuario");
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();

		rsp = usuarioService.registrarTicketUsuario(item);
		
		rsp.setBonificacion( usuarioService.calculaCreditoTotal(item).doubleValue() );
		
		
		return rsp;
	}
	
	@PostMapping(value = "/usuario/totalBonificacion")
	public @ResponseBody InformacionUsuarioRSP obtenerTotalBonificacion(@RequestBody Usuario item) {
		
		log.info("Entrando a obtenerTotalBonificacion");
		InformacionUsuarioRSP user = usuarioService.obtenerTotalBonificacion(item);
		
		return user;
	}
	
	@PostMapping(value = "/usuario/mediosBonificacion")
	public @ResponseBody InformacionUsuarioRSP obtenerMediosBonificacion(@RequestBody Usuario item) {
		
		log.info("Entrando a obtenerMediosBonificacion");
		InformacionUsuarioRSP user = usuarioService.obtenerMediosBonificacion(item);
		
		return user;
	}
	
	@PostMapping(value="/usuario/historicoBonificaciones")
	public @ResponseBody ListItemsRSP obtenerHistoricoBonificacionesPorUsuario( @RequestBody Usuario item ) {
		log.info("Entrando a obtenerHistoricoBonificacionesPorUsuario");
		ListItemsRSP rsp = usuarioService.obtienetHistoricosMediosBonificacionPorUsuario(item);
		
		return rsp;
	}
	
	@PostMapping(value="/usuario/historicoTickets")
	public @ResponseBody ListItemsRSP obtenerHistoricoTicketsPorUsuario( @RequestBody Usuario item ) {
		log.info("Entrando a obtenerHistoricoTicketsPorUsuario");
		ListItemsRSP rsp = usuarioService.obtieneTicketsPorUsuario(item);
		
		return rsp;
	}
}