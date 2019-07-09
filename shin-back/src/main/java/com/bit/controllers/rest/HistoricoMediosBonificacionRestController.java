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

import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.HistoricoMediosBonificacionService;
import com.bit.service.UsuarioService;

@RestController
@RequestMapping("/historicoMediosBonificacion")
public class HistoricoMediosBonificacionRestController {
	
	private static final Logger log= LoggerFactory.getLogger(HistoricoMediosBonificacionRestController.class);
	
	@Autowired
	private HistoricoMediosBonificacionService historicoMediosBonificacionService;
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getHistoricosMediosBonificacion() {
		
		log.info("Entrando a getHistoricosMediosBonificacion");
		ListItemsRSP rsp = historicoMediosBonificacionService.getHistoricosMediosBonificacion();

		return rsp;
	}
	
	@PostMapping(value="/listPorUsuario")
	public @ResponseBody ListItemsRSP getHistoricoMediosBonificacionPorUsuario(@RequestBody Usuario item){
		log.info("Entrando a getHistoricosMediosBonificacion");
		ListItemsRSP rsp = historicoMediosBonificacionService.getHistoricosMediosBonificacion();

		return rsp;
	}

	@PostMapping("/historicoMediosBonificacion/registrar")
	public SimpleResponse registrarHistoricoMediosBonificacion(@RequestBody HistoricoMediosBonificacion item) {
		
		log.info("Entrando a guardarHistoricosMediosBonificacion");
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();
		rsp = historicoMediosBonificacionService.registrarHistoricosMediosBonificacion(item);
		
		Usuario user = new Usuario();
		user.setIdUsuario( item.getUsuario().getIdUsuario() );
		rsp.setBonificacion( usuarioService.calculaCreditoTotal(user).doubleValue() );
		
		return rsp;
	}

	@PostMapping("/historicoMediosBonificacion/actualizar")
	public SimpleResponse actualizarHistoricoMediosBonificacion(@RequestBody HistoricoMediosBonificacion item) {
		
		log.info("Entrando a actualizarHistoricosMediosBonificacion");
		SimpleResponse rsp = historicoMediosBonificacionService.actualizarHistoricosMediosBonificacion(item);
		
		return rsp;
	}
}
