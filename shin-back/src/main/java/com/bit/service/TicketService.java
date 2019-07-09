package com.bit.service;

import java.util.List;

import com.bit.model.Ticket;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface TicketService {

	ListItemsRSP getTickets();
	SimpleResponse registrarTickets(Ticket item);
	SimpleResponse analizarOCR(List<String> lineas);
}
