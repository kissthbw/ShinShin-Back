package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
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
	
	/*
	 * metodo findMediosBonificacionByUser
	 */
	public List<MediosBonificacion> findMediosBonificacionByIdUser(Long idUser) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(MediosBonificacion.class);
		c.createAlias("usuario", "user");
		c.add(Restrictions.eq("user.idUsuario", idUser));
		c.addOrder(Property.forName("idMediosBonificacion").desc());
		
		return (List<MediosBonificacion>) c.list();
	}
}
