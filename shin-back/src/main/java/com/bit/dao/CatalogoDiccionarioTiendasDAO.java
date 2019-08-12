package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoDiccionarioTiendas;

@Repository
public class CatalogoDiccionarioTiendasDAO extends DAOTemplate<CatalogoDiccionarioTiendas, Long> {

	private static final long serialVersionUID = -5648564045744297857L;

	public List<CatalogoDiccionarioTiendas> getCatalogoDiccionarioTiendas() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoDiccionarioTiendas.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id").desc());

		return c.list();

	}

}
