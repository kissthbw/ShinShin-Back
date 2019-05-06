package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.HistoricoMediosBonificacion;

@Repository
public class HistoricoMediosBonificacionDAO extends DAOTemplate<HistoricoMediosBonificacion, Long> {

	private static final long serialVersionUID = -5802449578063521750L;

	public List<HistoricoMediosBonificacion> getHistoricosMediosBonificacion() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(HistoricoMediosBonificacion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idHistoricoMediosBonificacion").desc());

		return c.list();
	}

}
