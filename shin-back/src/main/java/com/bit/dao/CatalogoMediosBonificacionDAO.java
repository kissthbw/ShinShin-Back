package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoMediosBonificacion;

@Repository
public class CatalogoMediosBonificacionDAO extends DAOTemplate<CatalogoMediosBonificacion, Long> {

	private static final long serialVersionUID = 3628640656482549065L;

	public List<CatalogoMediosBonificacion> getCatalogoMediosBonificacion() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoMediosBonificacion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id_catalogo_medio_bonificacion").desc());

		return c.list();

	}
}
