package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.Contacto;

@Repository
public class ContactoDAO extends DAOTemplate<Contacto, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<Contacto> getContacto() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Contacto.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id").desc());

		return c.list();

	}

}
