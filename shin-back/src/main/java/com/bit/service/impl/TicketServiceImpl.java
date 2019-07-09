package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.TicketDAO;
import com.bit.model.Ticket;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Autowired
	private TicketDAO ticketDAO;

	@Override
	@Transactional
	public ListItemsRSP getTickets() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo lista de tickets");
		
		List<Ticket> list = ticketDAO.getTickets();
		
		rsp.setTickets(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarTickets(Ticket item) {
		
		log.info("Registrando ticket");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		item = ticketDAO.save(item);
		rsp.setId(item.getIdTicket());
		return rsp;
	}

	@Override
	public SimpleResponse analizarOCR(List<String> lineas) {
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		return rsp;
	}
}
