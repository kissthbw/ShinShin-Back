package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
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
	
	public Usuario findUserByUser(String usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.like("usuario", usuario));

		return (Usuario)c.uniqueResult();
	}
	
	public Usuario findUserByUserAndPassword(String usuario, String contrasenia) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("usuario", usuario));
		c.add(Restrictions.eq("contrasenia", contrasenia));
		
		return (Usuario) c.uniqueResult();
	}
}
