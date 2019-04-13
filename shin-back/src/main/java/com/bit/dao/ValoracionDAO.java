package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.Valoracion;

@Repository
public class ValoracionDAO extends DAOTemplate<Valoracion, Long> {

	private static final long serialVersionUID = 6759640124981932665L;

	public List<Valoracion> getValoraciones() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Valoracion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idProducto").desc());

		return c.list();

	}

}
