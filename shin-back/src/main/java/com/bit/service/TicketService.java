package com.bit.service;

import java.util.List;

import com.bit.model.Ticket;

public interface TicketService {

	List<Ticket> getTickets();

	void guardarTickets(Ticket item);

	void actualizarTickets(Ticket item);

}
