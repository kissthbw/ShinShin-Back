package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.Usuario;

@Repository
public class UsuarioDAO extends DAOTemplate<Usuario, Long> {

	private static final long serialVersionUID = 5303350054054307313L;

	public List<Usuario> getUsuarios() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idUsuario").desc());
		return c.list();
	}

}
