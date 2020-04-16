package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bit.model.ProductoFavorito;

@Repository
public class ProductoFavoritoDAO extends DAOTemplate<ProductoFavorito, Long> {

	private static final long serialVersionUID = 5524719696568527451L;

	public List<ProductoFavorito> getProductosFavoritosPorUsuario( Long idUsuario ) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductoFavorito.class);
		c.add( Restrictions.eq("usuario.idUsuario", idUsuario) );
		c.setMaxResults(100);
		c.addOrder(Property.forName("id").desc());

		return c.list();
	}

}
