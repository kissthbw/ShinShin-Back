package com.bit.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.bit.model.CatalogoTiendaPattern;

@Repository
public class CatalogoTiendaPatternDAO extends DAOTemplate<CatalogoTiendaPattern, Integer> {

	private static final long serialVersionUID = -4958839230167086351L;
	
	public List<CatalogoTiendaPattern> getPatternsByTienda( String idTienda ){
		StringBuilder sql = new StringBuilder(  );
		sql.append( " FROM catalogo_tienda_pattern" );
		sql.append( " WHERE idTienda = :idTienda" );
		Query q = getSessionFactory().getCurrentSession().createQuery( sql.toString() );
		q.setParameter("idTienda", idTienda);
		
		return q.list();
	}

}
