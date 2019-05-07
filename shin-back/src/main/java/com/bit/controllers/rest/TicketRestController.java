package com.bit.controllers.rest;

import java.util.List;

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

	@Autowired
	private TicketService ticketService;

	@GetMapping(value = "/list")
	public @ResponseBody List<Ticket> getTickets() {
		System.out.println("Get Ticket");
		List<Ticket> list = ticketService.getTickets();

		return list;
	}

	@PostMapping(value = "/ticket/registrar")
	public @ResponseBody SimpleResponse registrarTicket(@RequestBody Ticket item) {
		SimpleResponse rsp = ticketService.registrarTickets(item);

		return rsp;
	}
}
