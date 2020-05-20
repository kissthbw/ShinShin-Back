package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.Item;
import com.bit.model.dto.ResumenItem;

@Repository
public class CatalogoTipoProductoDAO extends DAOTemplate<CatalogoTipoProducto, Long>{

	private static final long serialVersionUID = 2518303895272541679L;

	public List<CatalogoTipoProducto> getCatalogoTipoProductos(){
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoTipoProducto.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("nombreTipoProducto").asc());
		c.add(Property.forName("active").eq(true));
		
		return c.list();
	}
	
	public BigInteger getProducts(Long productos) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(""
				+ "SELECT COUNT(*) FROM catalogo_tipo_producto a left join producto b on a.id_catalogo_tipo_producto=b.id_catalogo_tipo_producto where a.id_catalogo_tipo_producto="+productos+" and b.active=1 group by a.id_catalogo_tipo_producto ;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	public BigInteger obtieneDepartamentosRegistrados() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(""
				+ "SELECT COUNT(*) AS departamentos FROM catalogo_tipo_producto where active=1;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	public int eliminaDepartamentoProductos( CatalogoTipoProducto item ) {
		StringBuilder sql = new StringBuilder();
		sql.append( " UPDATE producto " );
		sql.append( " SET active = 0 " );
		sql.append( " WHERE id_catalogo_tipo_producto = :id " );
		
		Query q = getSessionFactory().getCurrentSession().createQuery( sql.toString() );
		q.setParameter("id", item.getIdCatalogoTipoProducto());
		int rows = q.executeUpdate();
		
		return rows;
	}

	public List<Item> obtieneTotalEscaneosPorTiendaDepartamentoAnio( int year, String departamento ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" MONTH(t.fecha) AS indice,");
		sql.append(" c.nombre_tipo_producto as topico,");
		sql.append(" COUNT(DISTINCT hb.id_ticket) AS total");
		sql.append(" FROM ticket t");
		sql.append(" LEFT JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append(" LEFT JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append(" LEFT JOIN catalogo_tipo_producto c ON p.id_catalogo_tipo_producto = c.id_catalogo_tipo_producto");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND c.nombre_tipo_producto = :departamento");
		sql.append(" GROUP BY topico, indice");
		sql.append(" ORDER BY topico, indice");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("departamento", departamento);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Item> obtieneTopDepartamentosEscaneados( int top, int year, long idUsuario ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" c.nombre_tipo_producto as topico,");
		sql.append(" COUNT(DISTINCT hb.id_ticket) AS total");
		sql.append(" FROM ticket t");
		sql.append(" LEFT JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append(" LEFT JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append(" LEFT JOIN catalogo_tipo_producto c ON p.id_catalogo_tipo_producto = c.id_catalogo_tipo_producto");
		sql.append(" LEFT JOIN historico_tickets ht ON ht.ticket_id_ticket = t.id_ticket");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND ht.usuario_id_usuario = :idUsuario");
		sql.append(" GROUP BY topico");
		sql.append(" ORDER BY total DESC");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("idUsuario", idUsuario);
		q.setMaxResults(top);
		
		List<Item> total = q.list();
		
		return total;
	}

	public List<ResumenItem> obtieneResumenDepartamento(){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" c.nombre_tipo_producto as topico,");
		sql.append(" COUNT(DISTINCT hb.id_ticket) AS totalEscaneos,");
		sql.append(" COUNT( hb.producto_id_producto) AS totalProductos,");
		sql.append(" SUM( p.cantidad_bonificacion) AS totalBonificaciones");
		sql.append(" FROM ticket t");
		sql.append(" LEFT JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append(" LEFT JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append(" LEFT JOIN catalogo_tipo_producto c ON p.id_catalogo_tipo_producto = c.id_catalogo_tipo_producto");
//		sql.append(" WHERE YEAR(t.fecha) = 2019");
		sql.append(" GROUP BY topico");
		sql.append(" ORDER BY topico");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).setResultTransformer( (Transformers.aliasToBean(ResumenItem.class)) );
//		q.setParameter("year", year);
		
		List<ResumenItem> list = q.list();
		
		return list;
	}
}
