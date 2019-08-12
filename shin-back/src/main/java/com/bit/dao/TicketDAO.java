package com.bit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
	
	public boolean existeTicketByTransaccionTienda(Ticket item) {
		SQLQuery q = getSessionFactory().getCurrentSession().
				createSQLQuery("SELECT id_ticket FROM ticket "
						+ "WHERE ticket_transaccion = :transaccion AND "
						+ "ticket_tienda = :tienda");
		q.setParameter("transaccion", item.getTicket_transaccion());
		q.setParameter("tienda", item.getTicket_tienda());
		
		q.setFirstResult(0);
		q.setMaxResults(50);
		List<Integer> list = q.list();
		
		if( list.isEmpty() ) {
			return false;
		}
		else {
			return true;
		}
	}
}
