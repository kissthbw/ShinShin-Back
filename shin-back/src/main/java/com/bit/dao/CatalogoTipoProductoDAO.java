package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.TipoProducto;

@Repository
public class TipoProductoDAO extends DAOTemplate<TipoProducto, Long> {

	private static final long serialVersionUID = 2518303895272541679L;

	public List<TipoProducto> getTipoProductos(){
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(TipoProducto.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id_tipo_producto").desc());
		
		return c.list();
			
	}

}
