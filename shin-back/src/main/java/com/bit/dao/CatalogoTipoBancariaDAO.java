package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTipoBancaria;

@Repository
public class CatalogoTipoBancariaDAO extends DAOTemplate<CatalogoTipoBancaria, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<CatalogoTipoBancaria> getCatalogoTipoBancaria() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoTipoBancaria.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idTipo").desc());

		return c.list();

	}

}
