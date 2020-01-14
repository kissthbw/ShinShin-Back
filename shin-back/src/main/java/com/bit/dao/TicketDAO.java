package com.bit.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
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
	
	//metodo correspondiente de estadisticas-tickets-detalle
	//metodo para obtener total de tickets registrados
	public BigInteger obtieneTotalTickets() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS totalTickets FROM ticket;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//metodo correspondiente de estadisticas-tickets-detalle
	//metodo para obtener total de tickets registrados por dia
	public List<Object> obtieneTotalTicketsPorDia() {
		SQLQuery q= getSessionFactory().getCurrentSession().createSQLQuery("" 
				+ "SELECT COUNT(*) AS totalTickets,\r\n" + 
				"DAYNAME(fecha) AS dia,\r\n" + 
				"(fecha) AS fecha\r\n" + 
				"FROM ticket\r\n" + 
				"GROUP BY fecha;");
		
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	//metodo correspondiente de estadisticas-tickets-detalle
	//metodo para obtener total de tickets registrados por semana
	public List<Object> obtieneTotalTicketsPorSemana() {
		SQLQuery q= getSessionFactory().getCurrentSession().createSQLQuery("" 
				+ "SELECT COUNT(*) AS totalTickets,\r\n" + 
				"WEEK(fecha) AS semana,\r\n" + 
				"YEAR(fecha) AS anio\r\n" + 
				"FROM ticket\r\n" + 
				"GROUP BY anio, semana;");
			
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	//metodo correspondiente de estadisticas-tickets-detalle
	//metodo para obtener total de tickets registrados por mes
	public List<Object> obtieneTotalTicketsPorMes() {
		SQLQuery q= getSessionFactory().getCurrentSession().createSQLQuery("" 
				+ "SELECT COUNT(*) AS totalTickets,\r\n" + 
				"MONTHNAME(fecha) AS mes,\r\n" + 
				"YEAR(fecha) AS anio\r\n" + 
				"FROM ticket\r\n" + 
				"GROUP BY anio, mes;");
				
		List<Object> total = (List<Object>)q.list();
			
		return total;
	}
	
	//metodo correspondiente de estadisticas-tickets-detalle
	//metodo para obtener total de tickets por tienda registrados por dia y hora
	public List<Object> obtieneTotalTicketsTiendaPorDiaHora() {
		SQLQuery q= getSessionFactory().getCurrentSession().createSQLQuery("" 
				+ "SELECT COUNT(*) AS totaltickets,\r\n" + 
				"(nombre_tienda) AS tienda,\r\n" + 
				"DAYNAME(fecha) AS dia,\r\n" + 
				"(hora) AS hora,\r\n" + 
				"(fecha) AS fecha\r\n" + 
				"FROM ticket\r\n" + 
				"GROUP BY fecha, hora, tienda;");
			
		List<Object> total = (List<Object>)q.list();
			
		return total;
	}
		
	//metodo correspondiente de estadisticas-tickets-detalle
	//metodo para obtener total de tickets por tienda registrados por semana y hora
	public List<Object> obtieneTotalTicketsTiendaPorSemanaHora() {
		SQLQuery q= getSessionFactory().getCurrentSession().createSQLQuery("" 
				+ "SELECT COUNT(*) AS totaltickets,\r\n" + 
				"(nombre_tienda) AS tienda,\r\n" + 
				"WEEK(fecha) AS semana,\r\n" + 
				"(hora) AS hora,\r\n" + 
				"YEAR(fecha) AS anio\r\n" + 
				"FROM ticket\r\n" + 
				"GROUP BY  anio, semana, hora, tienda;");
				
		List<Object> total = (List<Object>)q.list();
			
		return total;
	}
		
	//metodo correspondiente de estadisticas-tickets-detalle
	//metodo para obtener total de tickets por tienda registrados por mes y hora
	public List<Object> obtieneTotalTicketsTiendaPorMesHora() {
		SQLQuery q= getSessionFactory().getCurrentSession().createSQLQuery("" 
				+ "SELECT COUNT(*) AS totaltickets,\r\n" + 
				"(nombre_tienda) AS tienda,\r\n" + 
				"MONTHNAME(fecha) AS mes,\r\n" + 
				"(hora) AS hora,\r\n" + 
				"YEAR(fecha) AS anio\r\n" + 
				"FROM ticket\r\n" + 
				"GROUP BY anio, fecha, hora, tienda;");
					
		List<Object> total = (List<Object>)q.list();
				
		return total;
	}
}
