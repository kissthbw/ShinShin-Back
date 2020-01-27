package com.bit.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Usuario;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.EstadisticasRSP;
import com.bit.service.EstadisticasService;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasRestController { 
	
	private static final Logger log= LoggerFactory.getLogger(EstadisticasRestController.class);
	
	@Autowired
	private EstadisticasService estadisticasService;
	
	@GetMapping(value = "/usuarios")
	public @ResponseBody EstadisticasRSP getEstadisticasUsuarios() {
		
		log.info("Entrando a getEstadisticasUsuarios");
		EstadisticasRSP rsp = estadisticasService.obtieneEstadisticasUsuarios();

		return rsp;
	}
	
	@GetMapping(value = "/usuario/{id}")
	public @ResponseBody EstadisticasGeneralRSP getEstadisticasUsuarioDetalle(@PathVariable String id) {
		
		log.info("Entrando a getEstadisticasUsuarioDetalle");
		Usuario item = new Usuario();
		item.setIdUsuario( Long.parseLong(id) );
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasUsuarioDetalle( item );

		return rsp;
	}
	
	@GetMapping(value = "/general")
	public @ResponseBody EstadisticasGeneralRSP getEstadisticasGeneral() {
		
		log.info("Entrando a getEstadisticasGeneral");
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasGeneral();

		return rsp;
	}
	
	@GetMapping(value = "/marcas")
	public @ResponseBody EstadisticasGeneralRSP getEstadisticasMarcas() {
		
		log.info("Entrando a getEstadisticasMarcas");
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasMarcas();

		return rsp;
	}

	
}