package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bit.model.Ticket;
import com.bit.model.dto.Item;
import com.bit.model.dto.TicketItem;

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
	
	/*
	 * Guardar relacion usuario - ticket
	 */
	public void saveUsuarioTicket( Long idUsuario, Long idTicket ) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO historico_tickets ");
		sql.append(" (usuario_id_usuario,ticket_id_ticket)");
		sql.append(" VALUES (:idUsuario,:idTicket)");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		q.setParameter("idUsuario", idUsuario);
		q.setParameter("idTicket", idTicket);
		
		int i = q.executeUpdate();
		
//		return total;
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
	
	//metodo correspondiente de estadisticas-tickets
	//metodo para obtener total de tickets registrados
	public BigInteger obtieneTotalTickets() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(""
				+ "SELECT COUNT(*) AS total FROM ticket;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//metodo correspondiente de estadisticas-tickets
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
	
	//metodo correspondiente de estadisticas-tickets
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
	
	//metodo correspondiente de estadisticas-tickets
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
	
	//metodo correspondiente de estadisticas-tickets
	//metodo para obtener total de tickets por tienda registrados por dia y hora
	public List<Object> obtieneTotalTicketsTiendaPorDiaHora() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" 
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
	
	//metodo correspondiente de estadisticas-tickets
	//metodo para obtener total de tickets por tienda registrados por semana y hora
	public List<Object> obtieneTotalTicketsTiendaPorSemanaHora() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" 
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
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" 
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
	
	public List<Item> obtieneTicketsPorDiaMesAnio(int year, int month){
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(
				"SELECT COUNT(*) AS total, " +
						"DAY(fecha) AS indice " + 
						"FROM ticket " + 
						"WHERE YEAR(fecha) = :year " +
						"AND MONTH(fecha) = :month " +
						"GROUP BY indice ASC "
		).setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Item> obtieneTicketsPorSemanaMesAnio(int year, int month){
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS total, "
				+ "WEEK(fecha) AS indice "
				+ "FROM ticket "
				+ "WHERE YEAR(fecha) = :year "
				+ "AND MONTH(fecha) = :month "
				+ "GROUP BY indice").setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Item> obtieneTicketsPorMesAnio(Integer year) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS total, \r\n" + 
				"MONTH(fecha) AS indice\r\n" + 
				"FROM ticket\r\n" + 
				"WHERE YEAR(fecha) = :year\r\n" + 
				"GROUP BY indice  \r\n" + 
				"ORDER BY indice ASC").setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Item> obtieneTicketsPorTiendaDia(int year, int month) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS total, "
				+ " (nombre_tienda) AS topico, "
				+ " DAY(fecha) AS indice "
				+ " FROM ticket "
				+ " WHERE YEAR(fecha) = :year "
				+ " AND MONTH(fecha) = :month "
				+ " GROUP BY hora, indice, topico "
		).setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Item> obtieneTicketsPorTiendaSemana(int year, int month){
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS total, "
				+ " (nombre_tienda) AS topico, "
				+ " WEEK(fecha) AS indice "
				+ " FROM ticket "
				+ " WHERE YEAR(fecha) = :year "
				+ " AND MONTH(fecha) = :month "
				+ " GROUP BY topico, indice ").setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		return total;
	}
	
	public List<Item> obtieneTicketsPorTiendaMes(Integer year) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS total, "
				+ " (nombre_tienda) AS topico, "
				+ " MONTH(fecha) AS indice "
				+ " FROM ticket "
				+ " WHERE YEAR(fecha) = :year "
				+ " GROUP BY indice, topico "
				+ " ORDER BY indice ").setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	/*
	 * Metodos relacionados a las paginas de estadisticas-tickets-detalle, 
	 * estadisticas-tickets-detalle-segundoDetalle
	 */
	public List<TicketItem> obtieneHistoricoTickets() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" 	t.fecha as fecha,");
		sql.append(" 	COUNT(hb.id_ticket) as cantidad,");
		sql.append("     SUM(p.cantidad_bonificacion) as importe");
		sql.append(" FROM ticket t");
		sql.append(" INNER JOIN historico_bonificaciones hb ON hb.id_ticket = t.id_ticket");
		sql.append(" INNER JOIN producto p ON p.id_producto = hb.producto_id_producto");
		sql.append(" GROUP BY fecha");
		sql.append(" ORDER BY fecha DESC");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).setResultTransformer((Transformers.aliasToBean(TicketItem.class)));
		q.setMaxResults(100);
		
		List<TicketItem> total = q.list();
		
		return total;
	}
	
	public List<TicketItem> obtieneTicketsPorFecha(String date) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("     COUNT(h.id_ticket) as cantidad,");
		sql.append("     t.id_ticket as id,");
		sql.append("     t.nombre_tienda as tienda,");
		sql.append("     t.fecha as fecha,");
		sql.append("     t.hora as hora,");
		sql.append("     ht.usuario_id_usuario as idUsuario,");
		sql.append("     SUM(p.cantidad_bonificacion) as importe");
		sql.append(" FROM ");
		sql.append(" 	ticket t");
		sql.append("     INNER JOIN historico_bonificaciones h ON h.id_ticket = t.id_ticket");
		sql.append("     INNER JOIN historico_tickets ht ON ht.ticket_id_ticket = t.id_ticket");
		sql.append("     INNER JOIN producto p ON p.id_producto = h.producto_id_producto");
		sql.append(" WHERE fecha = :date");
		sql.append(" GROUP BY total, id, idUsuario");
		sql.append(" ORDER BY t.fecha DESC");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).setResultTransformer((Transformers.aliasToBean(TicketItem.class)));
		q.setParameter("date", date);
		
		List<TicketItem> total = q.list();
		
		return total;
	}
	
	public List<TicketItem> obtieneDetalleTicketPorId(Integer idTicket) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("     COUNT(h.producto_id_producto) as cantidad,");
		sql.append("     SUM(p.cantidad_bonificacion) as importe,");
		sql.append("     h.producto_id_producto as id,");
		sql.append("     p.nombre_producto as identificador");
		sql.append(" FROM ");
		sql.append(" 	historico_bonificaciones h");
		sql.append("     INNER JOIN producto p ON p.id_producto = h.producto_id_producto");
		sql.append(" WHERE");
		sql.append(" 	h.id_ticket = :idTicket");
		sql.append(" GROUP BY id");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).setResultTransformer((Transformers.aliasToBean(TicketItem.class)));
		q.setParameter("idTicket", idTicket);
		
		List<TicketItem> total = q.list();
		
		return total;
	}
}