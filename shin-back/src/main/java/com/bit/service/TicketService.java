package com.bit.service;

import com.bit.model.Ticket;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.request.OCRTicketRQT;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.dto.response.OCRTicketRSP;

public interface TicketService {

	ListItemsRSP getTickets();
	SimpleResponse registrarTickets(Ticket item);
	OCRTicketRSP analizarOCR(OCRTicketRQT rqt, boolean fake);
}
