package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bit.model.Producto;
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
	
	@Transactional
	public List<ProductosTiendas> getProductosPorIDYEmpresa(List<String> items) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductosTiendas.class);
		c.add(Restrictions.in("productoTienda", items));

		return ((Criteria) c).list();
	}
}
