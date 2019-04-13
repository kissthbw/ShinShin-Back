package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.PagoMovil;

@Repository
public class PagoMovilDAO extends DAOTemplate<PagoMovil, Long> {

	private static final long serialVersionUID = -6807032003670944883L;

	public List<PagoMovil> getPagosMoviles() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(PagoMovil.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("telMovil").desc());

		return c.list();

	}
}
