package com.bit.controllers.rest;

import java.util.List;

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
import com.bit.model.dto.SimpleResponse;
import com.bit.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketRestController {
	
	private static final Logger log= LoggerFactory.getLogger(TicketRestController.class);

	@Autowired
	private TicketService ticketService;

	@GetMapping(value = "/list")
	public @ResponseBody List<Ticket> getTickets() {
		
		log.info("Entrando a getTickets");
		List<Ticket> list = ticketService.getTickets();

		return list;
	}

	@PostMapping(value = "/ticket/registrar")
	public @ResponseBody SimpleResponse registrarTicket(@RequestBody Ticket item) {
		
		log.info("Entrando a registrarTickets");
		SimpleResponse rsp = ticketService.registrarTickets(item);

		return rsp;
	}
}
