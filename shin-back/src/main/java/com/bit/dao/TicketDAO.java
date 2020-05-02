package com.bit.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Ticket;
import com.bit.model.dto.Item;
import com.bit.model.dto.TicketItem;
import com.bit.model.dto.response.EstadisticaGeneralCSV;
import com.bit.model.report.EstadisticaGeneralTotalTicketCSV;

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
	public void saveUsuarioTicket(Long idUsuario, Long idTicket) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO historico_tickets ");
		sql.append(" (usuario_id_usuario,ticket_id_ticket)");
		sql.append(" VALUES (:idUsuario,:idTicket)");

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		q.setParameter("idUsuario", idUsuario);
		q.setParameter("idTicket", idTicket);

		int i = q.executeUpdate();

//		return total;
	}

	public boolean existeTicketByTransaccionTienda(Ticket item) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT id_ticket FROM ticket "
				+ "WHERE ticket_transaccion = :transaccion AND " + "ticket_tienda = :tienda");
		q.setParameter("transaccion", item.getTicket_transaccion());
		q.setParameter("tienda", item.getTicket_tienda());

		q.setFirstResult(0);
		q.setMaxResults(50);
		List<Integer> list = q.list();

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	// metodo correspondiente de estadisticas-tickets
	// metodo para obtener total de tickets registrados
	public BigInteger obtieneTotalTickets() {
		SQLQuery q = getSessionFactory().getCurrentSession()
				.createSQLQuery("" + "SELECT COUNT(*) AS total FROM ticket;");
		BigInteger total = (BigInteger) q.uniqueResult();

		return total;
	}

	// metodo correspondiente de estadisticas-tickets
	// metodo para obtener total de tickets registrados por dia
	public List<Object> obtieneTotalTicketsPorDia() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS totalTickets,\r\n"
				+ "DAYNAME(fecha) AS dia,\r\n" + "(fecha) AS fecha\r\n" + "FROM ticket\r\n" + "GROUP BY fecha;");

		List<Object> total = (List<Object>) q.list();

		return total;
	}

	// metodo correspondiente de estadisticas-tickets
	// metodo para obtener total de tickets registrados por semana
	public List<Object> obtieneTotalTicketsPorSemana() {
		SQLQuery q = getSessionFactory().getCurrentSession()
				.createSQLQuery("" + "SELECT COUNT(*) AS totalTickets,\r\n" + "WEEK(fecha) AS semana,\r\n"
						+ "YEAR(fecha) AS anio\r\n" + "FROM ticket\r\n" + "GROUP BY anio, semana;");

		List<Object> total = (List<Object>) q.list();

		return total;
	}

	// metodo correspondiente de estadisticas-tickets
	// metodo para obtener total de tickets registrados por mes
	public List<Object> obtieneTotalTicketsPorMes() {
		SQLQuery q = getSessionFactory().getCurrentSession()
				.createSQLQuery("" + "SELECT COUNT(*) AS totalTickets,\r\n" + "MONTHNAME(fecha) AS mes,\r\n"
						+ "YEAR(fecha) AS anio\r\n" + "FROM ticket\r\n" + "GROUP BY anio, mes;");

		List<Object> total = (List<Object>) q.list();

		return total;
	}

	// metodo correspondiente de estadisticas-tickets
	// metodo para obtener total de tickets por tienda registrados por dia y hora
	public List<Object> obtieneTotalTicketsTiendaPorDiaHora() {
		SQLQuery q = getSessionFactory().getCurrentSession()
				.createSQLQuery("" + "SELECT COUNT(*) AS totaltickets,\r\n" + "(nombre_tienda) AS tienda,\r\n"
						+ "DAYNAME(fecha) AS dia,\r\n" + "(hora) AS hora,\r\n" + "(fecha) AS fecha\r\n"
						+ "FROM ticket\r\n" + "GROUP BY fecha, hora, tienda;");

		List<Object> total = (List<Object>) q.list();

		return total;
	}

	// metodo correspondiente de estadisticas-tickets
	// metodo para obtener total de tickets por tienda registrados por semana y hora
	public List<Object> obtieneTotalTicketsTiendaPorSemanaHora() {
		SQLQuery q = getSessionFactory().getCurrentSession()
				.createSQLQuery("" + "SELECT COUNT(*) AS totaltickets,\r\n" + "(nombre_tienda) AS tienda,\r\n"
						+ "WEEK(fecha) AS semana,\r\n" + "(hora) AS hora,\r\n" + "YEAR(fecha) AS anio\r\n"
						+ "FROM ticket\r\n" + "GROUP BY  anio, semana, hora, tienda;");

		List<Object> total = (List<Object>) q.list();

		return total;
	}

	// metodo correspondiente de estadisticas-tickets-detalle
	// metodo para obtener total de tickets por tienda registrados por mes y hora
	public List<Object> obtieneTotalTicketsTiendaPorMesHora() {
		SQLQuery q = getSessionFactory().getCurrentSession()
				.createSQLQuery("" + "SELECT COUNT(*) AS totaltickets,\r\n" + "(nombre_tienda) AS tienda,\r\n"
						+ "MONTHNAME(fecha) AS mes,\r\n" + "(hora) AS hora,\r\n" + "YEAR(fecha) AS anio\r\n"
						+ "FROM ticket\r\n" + "GROUP BY anio, fecha, hora, tienda;");

		List<Object> total = (List<Object>) q.list();

		return total;
	}

	public List<Item> obtieneTicketsPorDiaMesAnio(int year, int month) {
		String qry = "SELECT COUNT(*) AS total, " + "DAY(fecha) AS indice " + "FROM ticket "
				+ "WHERE YEAR(fecha) = :year " + "AND MONTH(fecha) = :month " + "GROUP BY indice ORDER BY indice ASC";
		System.out.println(qry);
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(qry)
				.setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);

		List<Item> total = q.list();

		return total;
	}

	public List<Item> obtieneTicketsPorSemanaMesAnio(int year, int month) {
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery("SELECT COUNT(*) AS total, " + "WEEK(fecha) AS indice " + "FROM ticket "
						+ "WHERE YEAR(fecha) = :year " + "AND MONTH(fecha) = :month " + "GROUP BY indice")
				.setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);

		List<Item> total = q.list();

		return total;
	}

	public List<Item> obtieneTicketsPorMesAnio(Integer year) {
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery("SELECT COUNT(*) AS total, \r\n" + "MONTH(fecha) AS indice\r\n" + "FROM ticket\r\n"
						+ "WHERE YEAR(fecha) = :year\r\n" + "GROUP BY indice  \r\n" + "ORDER BY indice ASC")
				.setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);

		List<Item> total = q.list();

		return total;
	}

	public List<Item> obtieneTicketsPorTiendaDia(int year, int month) {
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery("SELECT COUNT(*) AS total, " + " (nombre_tienda) AS topico, " + " DAY(fecha) AS indice "
						+ " FROM ticket " + " WHERE YEAR(fecha) = :year " + " AND MONTH(fecha) = :month "
						+ " GROUP BY hora, indice, topico ")
				.setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);

		List<Item> total = q.list();

		return total;
	}

	public List<Item> obtieneTicketsPorTiendaSemana(int year, int month) {
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery("SELECT COUNT(*) AS total, " + " (nombre_tienda) AS topico, "
						+ " WEEK(fecha) AS indice " + " FROM ticket " + " WHERE YEAR(fecha) = :year "
						+ " AND MONTH(fecha) = :month " + " GROUP BY topico, indice ")
				.setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("year", year);
		q.setParameter("month", month);

		List<Item> total = q.list();
		return total;
	}

	public List<Item> obtieneTicketsPorTiendaMes(Integer year) {
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery("SELECT COUNT(*) AS total, " + " (nombre_tienda) AS topico, "
						+ " MONTH(fecha) AS indice " + " FROM ticket " + " WHERE YEAR(fecha) = :year "
						+ " GROUP BY indice, topico " + " ORDER BY indice ")
				.setResultTransformer((Transformers.aliasToBean(Item.class)));
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

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer((Transformers.aliasToBean(TicketItem.class)));
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

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer((Transformers.aliasToBean(TicketItem.class)));
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

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer((Transformers.aliasToBean(TicketItem.class)));
		q.setParameter("idTicket", idTicket);

		List<TicketItem> total = q.list();

		return total;
	}

	/**
	 * Sacar las estadisticas de los usuarios que sacaron tickets
	 * 
	 * @return
	 */
	public List<EstadisticaGeneralCSV> obtieneEstadisticasTickets() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   t.fecha as fecha, ");
		sql.append("   COUNT(ht.ticket_id_ticket) as totalUsuarios,");
		sql.append("   AVG(TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE())) AS totalEdadPromedio,");
		sql.append("   COUNT(IF(TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) BETWEEN 18 AND 24,1,NULL)) as avg1824,");
		sql.append("   COUNT(IF(TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) BETWEEN 25 AND 29,1,NULL)) as avg2529,");
		sql.append("   COUNT(IF(TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) BETWEEN 30 AND 39,1,NULL)) as avg3039,");
		sql.append("   COUNT(IF(TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) BETWEEN 40 AND 49,1,NULL)) as avg4049,");
		sql.append("   COUNT(IF(TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) BETWEEN 50 AND 59,1,NULL)) as avg5059,");
		sql.append("   COUNT(IF(TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) > 60,1,NULL)) as avgMAS60,");
		sql.append("   COUNT(IF(u.id_catalogo_sexo=1,1,NULL)) as hombres,");
		sql.append("   COUNT(IF(u.id_catalogo_sexo=2,1,NULL)) as mujeres");
		sql.append("   FROM ticket t ");
		sql.append("   INNER JOIN historico_tickets ht ON ht.ticket_id_ticket = t.id_ticket ");
		sql.append("   INNER JOIN usuario u ON u.id_usuario = ht.usuario_id_usuario ");
		sql.append("   GROUP BY fecha ");
		sql.append("   ORDER BY fecha DESC;");
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());

		q.setResultTransformer(Transformers.aliasToBean(EstadisticaGeneralCSV.class));

		List<EstadisticaGeneralCSV> total = q.list();

		return total;

	}

	/**
	 * Obtiene el total de tickets escaneados por tiendas
	 * 
	 * @return
	 */
	public List<EstadisticaGeneralCSV> calculaTotalTicketEscaneadosTiendas(List<EstadisticaGeneralCSV> dataPrincipal) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   t.fecha AS fecha,");
		sql.append("   COUNT(t.nombre_tienda) AS totalEscaneos");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_tickets ht ON ht.ticket_id_ticket = t.id_ticket ");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   WHERE t.fecha IS NOT NULL");
		sql.append("    GROUP BY t.fecha");
		sql.append("   ORDER BY t.fecha DESC;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());

		q.setResultTransformer(Transformers.aliasToBean(EstadisticaGeneralTotalTicketCSV.class));

		List<EstadisticaGeneralTotalTicketCSV> total = q.list();

		// Mezcla
		for(int x = 0; x<total.size(); x++) {
			dataPrincipal.get(x).setTotalEscaneadosTiendas(
					total.get(x).getTotalEscaneos());
		}
		
		return dataPrincipal;
	}
	
	/**
	 * Obtiene el total de tickets escaneados por una tienda en una fecha.
	 * 
	 * @return
	 */
	public EstadisticaGeneralTotalTicketCSV  obtieneEstadisticaGeneralTotalTicketCSVPorFechaYTienda(Date fecha, String nomTienda) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("   t.fecha AS fecha,");
		sql.append("   COUNT(t.nombre_tienda) AS totalEscaneos");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append("   WHERE t.fecha=:date and t.nombre_tienda=:nomT");
		sql.append("   GROUP BY t.fecha;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		q.setParameter("date", fecha);
		q.setParameter("nomT", nomTienda);		
		q.setResultTransformer(Transformers.aliasToBean(EstadisticaGeneralTotalTicketCSV.class));
		
		EstadisticaGeneralTotalTicketCSV total = q.uniqueResult() != null ? 
				(EstadisticaGeneralTotalTicketCSV)q.uniqueResult(): null;
		
		
		return total;
		
	}
	

	/**
	 * Obtiene todos los departamentos donde a habido tickets.
	 * 
	 * @return
	 */
	public List<String> obtieneTiendasConTicket() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("   t.nombre_tienda as nombre");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_tickets ht ON ht.ticket_id_ticket = t.id_ticket");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append("   INNER JOIN catalogo_tienda ct ON p.id_catalogo_tienda = ct.id_catalogo_tienda");
		sql.append("   GROUP BY nombre");
		sql.append("   order by nombre asc;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		
		List<String> total = q.list();
		
		return total;
	}
	
	/**
	 * Obtiene todos los departamentos donde a habido tickets.
	 * 
	 * @return
	 */
	public List<String> obtieneDepartamentosConTicket() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("   ctp.nombre_tipo_producto AS nombre");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append("   INNER JOIN catalogo_tipo_producto ctp ON p.id_catalogo_tipo_producto = ctp.id_catalogo_tipo_producto");
		sql.append("   GROUP BY nombre;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		
		List<String> total = q.list();
		
		return total;
	}
	
	/**
	 * CALCULA LOS PRODUCTOS ESCANEADOS EN UN TICKET POR DEPARTAMENTO
	 * 
	 * @return
	 */
	public List<EstadisticaGeneralCSV> calculaTotalProductosEscaneados(List<EstadisticaGeneralCSV> dataPrincipal) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("   t.fecha AS fecha,");
		sql.append("   COUNT(ctp.nombre_tipo_producto) AS totalEscaneos");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_tickets ht ON ht.ticket_id_ticket = t.id_ticket ");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto ");
		sql.append("   INNER JOIN catalogo_tipo_producto ctp ON p.id_catalogo_tipo_producto = ctp.id_catalogo_tipo_producto");
		sql.append("    GROUP BY t.fecha");
		sql.append("   ORDER BY t.fecha DESC;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		
		q.setResultTransformer(Transformers.aliasToBean(EstadisticaGeneralTotalTicketCSV.class));
		
		List<EstadisticaGeneralTotalTicketCSV> total = q.list();
		
		// Mezcla
		for(int x = 0; x<total.size(); x++) {
			if(x<dataPrincipal.size()) {
				dataPrincipal.get(x).setTotalProductosEscaneadosDesptos(
						total.get(x).getTotalEscaneos());
			}
		}
		
		return dataPrincipal;
	}
	/**
	 * Obtiene el total de tickets escaneados por una departamento en una fecha.
	 * 
	 * @return
	 */
	public EstadisticaGeneralTotalTicketCSV  obtieneEstadisticaGeneralTotalTicketCSVPorFechaYDepto(Date fecha, String nomDepto) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("   t.fecha AS fecha,");
		sql.append("   COUNT(ctp.nombre_tipo_producto) AS totalEscaneos");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append("   INNER JOIN catalogo_tipo_producto ctp ON p.id_catalogo_tipo_producto = ctp.id_catalogo_tipo_producto");
		sql.append("   WHERE t.fecha=:date and ctp.nombre_tipo_producto=:nom");
		sql.append("   GROUP BY t.fecha;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		q.setParameter("date", fecha);
		q.setParameter("nom", nomDepto);		
		q.setResultTransformer(Transformers.aliasToBean(EstadisticaGeneralTotalTicketCSV.class));
		
		EstadisticaGeneralTotalTicketCSV total = q.uniqueResult() != null ? 
				(EstadisticaGeneralTotalTicketCSV)q.uniqueResult(): null;
				
				
				return total;
				
	}
	
	/**
	 * Las marcas de los productos que tienen los tickets escaneados.
	 * 
	 * @return
	 */
	public List<String> obtieneMarcasConTicket() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("   cm.nombre_marca AS nombre");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append("   INNER JOIN catalogo_marca cm ON p.id_catalogo_marca = cm.id_catalogo_marca");
		sql.append("   GROUP BY nombre");
		sql.append("   ORDER BY nombre ASC;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		
		List<String> total = q.list();
				
		return total;
	}

	/**
	 * CALCULA LOS PRODUCTOS ESCANEADOS EN UN TICKET POR marca
	 * 
	 * @return
	 */
	public List<EstadisticaGeneralCSV> calculaTotalMarcaProductosEscaneados(List<EstadisticaGeneralCSV> dataPrincipal) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   t.fecha AS fecha,");
		sql.append("   COUNT(cm.nombre_marca) AS totalEscaneos");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_tickets ht ON ht.ticket_id_ticket = t.id_ticket ");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto ");
		sql.append("   INNER JOIN catalogo_marca cm ON p.id_catalogo_marca = cm.id_catalogo_marca");
		sql.append("    GROUP BY t.fecha");
		sql.append("   ORDER BY t.fecha DESC;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());

		q.setResultTransformer(Transformers.aliasToBean(EstadisticaGeneralTotalTicketCSV.class));

		List<EstadisticaGeneralTotalTicketCSV> total = q.list();

		// Mezcla
		for(int x = 0; x<total.size(); x++) {
			if(x<dataPrincipal.size()) {
				dataPrincipal.get(x).setTotalProductosEscaneadosMarcas(
						total.get(x).getTotalEscaneos());
			}
		}
		
		return dataPrincipal;
	}
	/**
	 * Obtiene el total de tickets escaneados por una marca en una fecha.
	 * 
	 * @return
	 */
	public EstadisticaGeneralTotalTicketCSV  obtieneEstadisticaGeneralTotalTicketCSVPorFechaYMarca(Date fecha, String nomDepto) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("   t.fecha AS fecha,");
		sql.append("   COUNT(cm.nombre_marca) AS totalEscaneos");
		sql.append("   FROM ticket t");
		sql.append("   INNER JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append("   INNER JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append("   INNER JOIN catalogo_marca cm ON p.id_catalogo_marca = cm.id_catalogo_marca");
		sql.append("   WHERE t.fecha=:date and cm.nombre_marca=:nom");
		sql.append("   GROUP BY t.fecha;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		q.setParameter("date", fecha);
		q.setParameter("nom", nomDepto);		
		q.setResultTransformer(Transformers.aliasToBean(EstadisticaGeneralTotalTicketCSV.class));
		
		EstadisticaGeneralTotalTicketCSV total = q.uniqueResult() != null ? 
				(EstadisticaGeneralTotalTicketCSV)q.uniqueResult(): null;
		
		
		return total;
		
	}
}