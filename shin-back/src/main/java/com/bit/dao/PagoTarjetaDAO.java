package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.PagoTarjeta;

@Repository
public class PagoTarjetaDAO extends DAOTemplate<PagoTarjeta, Long> {

	private static final long serialVersionUID = 8479084015230708548L;

	public List<PagoTarjeta> getPagosTarjetas() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(PagoTarjeta.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("numTarjeta").desc());
		return c.list();

	}

}
