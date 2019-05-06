package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoMarca;

@Repository
public class CatalogoMarcaDAO extends DAOTemplate<CatalogoMarca, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<CatalogoMarca> getMarca() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoMarca.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id_marca").desc());

		return c.list();

	}

}
