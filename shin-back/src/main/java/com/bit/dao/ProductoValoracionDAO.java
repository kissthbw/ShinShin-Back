package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.ProductoValoracion;

@Repository
public class ProductoValoracionDAO extends DAOTemplate<ProductoValoracion, Long> {

	private static final long serialVersionUID = 5524719696568527451L;

	public List<ProductoValoracion> getProductosValoracion() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductoValoracion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idProductoValoracion").desc());

		return c.list();
	}

}
