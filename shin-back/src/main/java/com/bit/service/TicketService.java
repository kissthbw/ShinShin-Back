package com.bit.service;

import java.util.List;

import com.bit.model.Ticket;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.TicketItem;
import com.bit.model.dto.request.OCRTicketRQT;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.dto.response.OCRTicketRSP;

public interface TicketService {

	ListItemsRSP getTickets();
	SimpleResponse registrarTickets(Ticket item);
	OCRTicketRSP analizarOCR(OCRTicketRQT rqt, boolean fake);
	ListItemsRSP getDetalleTicket(Long id);
	
	/*
	 * Metodos relacionados a las paginas de estadisticas-tickets-detalle, 
	 * estadisticas-tickets-detalle-segundoDetalle
	 */
	List<TicketItem> obtieneTicketsPorFecha(String fecha);
	List<TicketItem> obtieneDetalleTicketPorId(Integer idTicket);
}
