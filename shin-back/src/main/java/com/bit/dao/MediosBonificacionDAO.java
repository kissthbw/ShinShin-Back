package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.MediosBonificacion;

@Repository
public class MediosBonificacionDAO extends DAOTemplate<MediosBonificacion, Long> {

	private static final long serialVersionUID = -1057956020420878666L;

	public List<MediosBonificacion> getMediosBonificacion() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(MediosBonificacion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idMediosBonificacion").desc());

		return c.list();

	}

}
