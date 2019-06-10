package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTienda;

@Repository
public class CatalogoTiendaDAO extends DAOTemplate<CatalogoTienda, Long> {

	private static final long serialVersionUID = -3591680225533837181L;

	public List<CatalogoTienda> getCatalogoTienda() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoTienda.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idCatalogoTienda").desc());
		
		return c.list();
	}
}
