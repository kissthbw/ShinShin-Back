package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.ProductosTiendas;

@Repository
public class ProductosTiendasDAO extends DAOTemplate<ProductosTiendas, Long> {

	private static final long serialVersionUID = -383483471996532835L;

	public List<ProductosTiendas> getProductosTiendas() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductosTiendas.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idProductoTienda").desc());

		return c.list();
	}
}
