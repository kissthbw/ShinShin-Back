package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
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
	
	public List<ProductoValoracion> getValoracionPorProdcutoyUsuario( ProductoValoracion item ) {
		
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductoValoracion.class);
		c.add( Restrictions.eq("usuario.idUsuario", item.getUsuario().getIdUsuario()) );
		c.add( Restrictions.eq("producto.idProducto", item.getProducto().getIdProducto()) );
		c.setMaxResults(50);
		c.addOrder(Property.forName("idProductoValoracion").desc());

		return c.list();
	}

}
