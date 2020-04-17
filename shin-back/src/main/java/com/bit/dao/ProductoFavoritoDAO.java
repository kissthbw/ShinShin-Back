package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
	
	public boolean deletePorProductoYUsuario( Long idProducto, Long idUsuario ) {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM producto_favorito");
		sql.append(" WHERE id_usuario = :idUsuario");
		sql.append(" AND id_producto = :idProducto");
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		query.setParameter("idProducto", idProducto);
		query.setParameter("idUsuario", idUsuario);
		
		int n = query.executeUpdate();
		
		if ( n == 0 ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean existeFavoritoPorProductoYUsuario( Long idProducto, Long idUsuario ) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(id)");
		sql.append(" FROM producto_favorito");
		sql.append(" WHERE id_usuario = :idUsuario");
		sql.append(" AND id_producto = :idProducto");
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		query.setParameter("idProducto", idProducto);
		query.setParameter("idUsuario", idUsuario);
		
		Number n = (Number)query.uniqueResult();
		
		if ( n.longValue() == 0 ) {
			return false;
		}
		else {
			return true;
		}
	}

}
