package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTipoProducto;

@Repository
public class CatalogoTipoProductoDAO extends DAOTemplate<CatalogoTipoProducto, Long>{

	private static final long serialVersionUID = 2518303895272541679L;

	public List<CatalogoTipoProducto> getCatalogoTipoProductos(){
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoTipoProducto.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idCatalogoTipoProducto").desc());
		
		return c.list();
			
	}
	
	public BigInteger obtieneDepartamentosRegistrados() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(""
				+ "SELECT COUNT(*) AS departamentos FROM catalogo_tipo_producto;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
}
