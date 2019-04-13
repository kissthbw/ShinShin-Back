package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.TicketDAO;
import com.bit.model.Ticket;
import com.bit.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDAO ticketDAO;

	@Override
	@Transactional
	public List<Ticket> getTickets() {
		List<Ticket> list = ticketDAO.getTickets();
		return list;
	}

	@Override
	@Transactional
	public void guardarTickets(Ticket item) {
		ticketDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarTickets(Ticket item) {
		ticketDAO.save(item);

	}

}
