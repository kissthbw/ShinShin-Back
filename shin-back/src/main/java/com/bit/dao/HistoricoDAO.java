package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.Historico;

@Repository
public class HistoricoDAO extends DAOTemplate<Historico, Long> {

	private static final long serialVersionUID = -4587165455829577137L;

	public List<Historico> getHistoricos() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Historico.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("totalCompras").desc());

		return c.list();
	}

}
