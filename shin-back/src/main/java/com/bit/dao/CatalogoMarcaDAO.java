package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
import org.jfree.util.Log;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.Item;

@Repository
public class CatalogoMarcaDAO extends DAOTemplate<CatalogoMarca, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<CatalogoMarca> getCatalogoMarca() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoMarca.class);
		c.setMaxResults(50);
		//c.addOrder(Property.forName("idCatalogoMarca").desc());
		c.addOrder(Order.asc("nombreMarca"));
		c.add(Property.forName("active").eq(1));
		
		return c.list();
	}
	
	public BigInteger getProducts(Long productos) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(""
				+ "SELECT COUNT(*) FROM catalogo_marca a left join producto b on a.id_catalogo_marca=b.id_catalogo_marca where a.id_catalogo_marca="+productos+" and b.active=1 group by a.id_catalogo_marca ;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	
	//metodos correspondientes a estadisticas-marcas
	//metodo para obtener total de marcas registradas
	public BigInteger obtieneTotalMarcas() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS marcas FROM catalogo_marca;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//metodos correspondientes a estadisticas-marcas
	//metodo para obtener total de productos registrados
	public BigInteger obtieneTotalProductos() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS productos FROM producto;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//metodos correspondientes a estadisticas-marcas
	//metodo para obtener total de tickets registrados
	public BigInteger obtieneTotalTicketsEscaneados() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS totalTickets FROM ticket;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//metodos correspondientes a estadisticas-marcas
	//metodo para obtener total de departamentos registrados
	public BigInteger obtieneTotalDepartamentos() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS departamentos FROM catalogo_tipo_producto;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//metodos correspondientes a estadisticas-marcas
	//metodo para obtener total de tiendas registradas
	public BigInteger obtieneTotalTiendas() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) AS tiendas FROM catalogo_tienda;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	public List<Item> obtieneTopMarcasEscaneadas( int top, int year, long idUsuario ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" c.nombre_marca as topico,");
		sql.append(" COUNT(DISTINCT hb.id_ticket) AS total");
		sql.append(" FROM ticket t");
		sql.append(" LEFT JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append(" LEFT JOIN producto p ON hb.producto_id_producto = p.id_producto");
		sql.append(" LEFT JOIN catalogo_marca c ON p.id_catalogo_marca = c.id_catalogo_marca");
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
	public int eliminaMarcaProductos( CatalogoMarca item ) {
		Log.info("Entrando eliminaMarcaProductos");
		StringBuilder sql = new StringBuilder();
		sql.append( " UPDATE producto " );
		sql.append( " SET active = 0 " );
		sql.append( " WHERE id_catalogo_marca = :id " );
		Query q = getSessionFactory().getCurrentSession().createQuery( sql.toString() );
		q.setParameter("id", item.getIdCatalogoMarca());
		int rows = q.executeUpdate();
		
		return rows;
	}
}
