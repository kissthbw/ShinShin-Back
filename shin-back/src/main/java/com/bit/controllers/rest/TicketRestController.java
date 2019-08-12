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

import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.request.OCRTicketRQT;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketRestController {
	
	private static final Logger log= LoggerFactory.getLogger(TicketRestController.class);

	@Autowired
	private TicketService ticketService;

	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getTickets() {
		
		log.info("Entrando a getTickets");
		ListItemsRSP rsp = ticketService.getTickets();

		return rsp;
	}
	
	@PostMapping(value="/listPorUsuario")
	public @ResponseBody ListItemsRSP getTicketsPorUsuario(@RequestBody Usuario item){
		log.info("Entrando a getHistoricosMediosBonificacion");
//		ListItemsRSP rsp = ticketService.getTicketsPorUsuario(item);

		return null;
	}

	@PostMapping(value = "/ticket/registrar")
	public @ResponseBody SimpleResponse registrarTicket(@RequestBody Ticket item) {
		
		log.info("Entrando a registrarTickets");
		SimpleResponse rsp = ticketService.registrarTickets(item);

		return rsp;
	}
	
	@PostMapping(value = "/analizar")
	public @ResponseBody SimpleResponse analizarTicket(@RequestBody OCRTicketRQT rqt) {
		
		log.info("Entrando a analizarTicket");
		SimpleResponse rsp = ticketService.analizarOCR(rqt, true);

		return rsp;
	}
	
	@PostMapping(value = "/analizar2")
	public @ResponseBody SimpleResponse analizarTicket2(@RequestBody OCRTicketRQT rqt) {
		
		log.info("Entrando a analizarTicket");
		SimpleResponse rsp = ticketService.analizarOCR(rqt, false);

		return rsp;
	}
}
