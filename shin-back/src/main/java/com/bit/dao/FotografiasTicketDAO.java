package com.bit.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bit.model.FotografiasTicket;

@Repository
public class FotografiasTicketDAO extends DAOTemplate<FotografiasTicket, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	/*
	 * Guardar relacion 
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
	}
}
