package com.bit.controllers.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.Usuario;
import com.bit.model.dto.Category;
import com.bit.model.dto.response.EstadisticasBonificacionRSP;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.EstadisticasRSP;
import com.bit.service.EstadisticasService;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasRestController { 
	
	private static final Logger log= LoggerFactory.getLogger(EstadisticasRestController.class);
	
	@Autowired
	private EstadisticasService estadisticasService;
	
	@CrossOrigin
	@GetMapping(value = "/usuarios")
	public @ResponseBody EstadisticasRSP getEstadisticasUsuarios() {
		
		log.info("Entrando a getEstadisticasUsuarios");
		EstadisticasRSP rsp = estadisticasService.obtieneEstadisticasUsuarios();

		return rsp;
	}
	
	@CrossOrigin
	@GetMapping(value = "/usuario/{id}")
	public @ResponseBody EstadisticasGeneralRSP getEstadisticasUsuarioDetalle(@PathVariable String id) {
		
		log.info("Entrando a getEstadisticasUsuarioDetalle");
		Usuario item = new Usuario();
		item.setIdUsuario( Long.parseLong(id) );
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasUsuarioDetalle( item );

		return rsp;
	}
	
	@CrossOrigin
	@GetMapping(value = "/general")
	public @ResponseBody EstadisticasGeneralRSP getEstadisticasGeneral() {
		
		log.info("Entrando a getEstadisticasGeneral");
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasGeneral();

		return rsp;
	}
	
	@CrossOrigin
	@GetMapping(value = "/marcas")
	public @ResponseBody EstadisticasGeneralRSP getEstadisticasMarcas() {
		
		log.info("Entrando a getEstadisticasMarcas");
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasMarcas();

		return rsp;
	}

	/**
	 * 
	 * @param tipo valor que puede ser 1 (depositos),2(bonificaciones) o 3(recargas)
	 * @param categoria valor que puede ser d (dia), s (semana), m (mes)
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/bonificaciones-general")
	public @ResponseBody EstadisticasBonificacionRSP getBonificacionesGeneral( @RequestParam(required = false) String tipo,
			@RequestParam(required = false) String categoria) {

		
		log.info("Entrando a getBonificacionesGeneral");
		log.info("Params: tipo: {}, categoria: {}", tipo, categoria);
		
		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(tipo, categoria);

		return rsp;
	}
	
	/**
	 * 
	 * @param categoria valor que puede ser d (dia), s (semana), m (mes)
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/bonificaciones-recargas")
	public @ResponseBody List<Category> getBonificacionesRecargas( @RequestParam(required = false) String categoria ) {

		log.info("Entrando a getBonificacionesRecargas");
		log.info("Param: categoria: {}", categoria);
		
		List<Category> rsp = estadisticasService.obtieneRecargasPorCompania(categoria);

		return rsp;
	}
	
	@CrossOrigin
	@GetMapping(value = "/tickets")
	public @ResponseBody EstadisticasRSP getEstadisticasTickets() {
		
		log.info("Entrando a getEstadisticasTickets");
		EstadisticasRSP rsp = estadisticasService.obtieneEstadisticasTickets();
		
		return rsp;
	}
}