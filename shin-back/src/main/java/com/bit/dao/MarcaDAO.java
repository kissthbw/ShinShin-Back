package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.Marca;

@Repository
public class MarcaDAO extends DAOTemplate<Marca, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<Marca> getMarca() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Marca.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id_marca").desc());

		return c.list();

	}

}
