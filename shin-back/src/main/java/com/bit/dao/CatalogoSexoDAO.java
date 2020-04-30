package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoSexo;

@Repository
public class CatalogoSexoDAO extends DAOTemplate<CatalogoSexo, Long> {

	private static final long serialVersionUID = -3591680225533837181L;

	public List<CatalogoSexo> obtieneCatalogo() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoSexo.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idCatalogoSexo").desc());
		return c.list();
	}

}
