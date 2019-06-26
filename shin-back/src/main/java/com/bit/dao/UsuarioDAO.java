package com.bit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bit.model.Usuario;

@Repository
public class UsuarioDAO extends DAOTemplate<Usuario, Long> {

	private static final long serialVersionUID = 5303350054054307313L;

	public List<Usuario> getUsuarios() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idUsuario").desc());
		return c.list();
	}
	
	public List<Long> getTicketsPorUsuario(Usuario item) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT ticket_id_ticket FROM historico_tickets WHERE usuario_id_usuario = :idUsuario");
		q.setParameter("idUsuario", item.getIdUsuario());
		q.setFirstResult(0);
		q.setMaxResults(50);
		List<Object> list = q.list();
		List<Long> tmpList = new ArrayList<>();
		
		for( Object o : list ) {
			tmpList.add( Long.parseLong(o.toString()) );
		}
		
		
		return tmpList ;
	}
	
	public Usuario findUserByUser(String usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.like("usuario", usuario));

		return (Usuario)c.uniqueResult();
	}
	
	public Usuario findUserByUserAndPassword(String usuario, String contrasenia) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("usuario", usuario));
		c.add(Restrictions.eq("contrasenia", contrasenia));
		
		return (Usuario) c.uniqueResult();
	}
}
