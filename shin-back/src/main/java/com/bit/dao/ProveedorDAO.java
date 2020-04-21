package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bit.model.Proveedor;

@Repository
public class ProveedorDAO extends DAOTemplate<Proveedor, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<Proveedor> getProveedores() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Proveedor.class);
		c.setMaxResults(200);
		c.addOrder(Order.desc("id"));
		
		return c.list();
	}
	
	public Proveedor finfByEmail( String email ) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Proveedor.class);
		c.add( Restrictions.eq("email", email) );
		c.setMaxResults(200);
		c.addOrder(Order.desc("id"));
		
		return (Proveedor)c.uniqueResult();
	}
}
