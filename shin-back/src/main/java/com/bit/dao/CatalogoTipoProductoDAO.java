package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTipoProducto;

@Repository
public class CatalogoTipoProductoDAO extends DAOTemplate<CatalogoTipoProducto, Long>{

	private static final long serialVersionUID = 2518303895272541679L;

	public List<CatalogoTipoProducto> getCatalogoTipoProductos(){
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoTipoProducto.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id_catalogo_tipo_producto").desc());
		
		return c.list();
			
	}

}
