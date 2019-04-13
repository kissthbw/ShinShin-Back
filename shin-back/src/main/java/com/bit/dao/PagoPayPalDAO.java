package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.PagoPayPal;

@Repository
public class PagoPayPalDAO extends DAOTemplate<PagoPayPal, Long> {

	private static final long serialVersionUID = -5383815009286452124L;

	public List<PagoPayPal> getPagosPayPal() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(PagoPayPal.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("codigoCuenta").desc());

		return c.list();
	}

}
