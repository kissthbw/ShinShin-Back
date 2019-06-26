package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bit.model.Ticket;

@Repository
public class TicketDAO extends DAOTemplate<Ticket, Long> {

	private static final long serialVersionUID = -1696035387615043202L;

	public List<Ticket> getTickets() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Ticket.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idTicket").desc());

		return c.list();
	}
	
	public List<Ticket> getTicketsPorUsuario(List<Long> ids) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Ticket.class);
		c.setMaxResults(50);
		c.add(Restrictions.in("idTicket", ids));
		c.addOrder(Property.forName("idTicket").desc());

		return c.list();
	}
}
