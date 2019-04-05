package com.bit.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TiketsRestController {
	
	@GetMapping(value = "/list")
	public void getTickets() {
		System.out.println("Get Tickets");
	}
}
