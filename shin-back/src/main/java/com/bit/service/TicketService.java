package com.bit.service;

import java.util.List;

import com.bit.model.Ticket;
import com.bit.model.dto.SimpleResponse;

public interface TicketService {

	List<Ticket> getTickets();

	SimpleResponse registrarTickets(Ticket item);

}
