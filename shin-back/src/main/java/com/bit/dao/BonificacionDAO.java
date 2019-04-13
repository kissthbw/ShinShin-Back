package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.Bonificacion;

@Repository
public class BonificacionDAO extends DAOTemplate<Bonificacion, Long> {

	private static final long serialVersionUID = 4600761196044286655L;

	public List<Bonificacion> getBonificaciones() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Bonificacion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idBonificacion").desc());

		return c.list();
	}

}
