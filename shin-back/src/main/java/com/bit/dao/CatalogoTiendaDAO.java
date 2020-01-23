package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTienda;
import com.bit.model.dto.Item;
import com.bit.model.dto.ResumenItem;

@Repository
public class CatalogoTiendaDAO extends DAOTemplate<CatalogoTienda, Long> {

	private static final long serialVersionUID = -3591680225533837181L;

	public List<CatalogoTienda> getCatalogoTienda() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoTienda.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idCatalogoTienda").desc());
		
		return c.list();
	}
	
	//metodo correspondiente a estadisticas-general
	//metodo que obtiene total escaneos por tienda al mes
	public List<Object> obtieneTotalEscaneosTiendaPorMes() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS totaltickets,\r\n" + 
				"(nombre_tienda) AS tienda,\r\n" + 
				"MONTHNAME(fecha) AS mes,\r\n" + 
				"YEAR(fecha) AS anio\r\n" + 
				"FROM ticket\r\n" + 
				"GROUP BY anio, fecha, tienda;");
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	public List<Item> obtieneTotalEscaneosPorTiendaMesAnio( int year, String tienda ) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS total,\r\n" + 
				" (nombre_tienda) AS topico,\r\n" + 
				" MONTH(fecha) AS indice\r\n" +  
				" FROM ticket\r\n" + 
				" WHERE YEAR(fecha) = :year" +
				" AND nombre_tienda = :tienda" +
				" GROUP BY fecha, topico " + 
				" ORDER BY topico, indice").setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("tienda", tienda);
		
		List<Item> total = q.list();
		
		return total;
	}
	

	//Obtiene resumen por tienda
	public List<ResumenItem> obtieneResumenTiendas(){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" (t.nombre_tienda) AS topico,");
		sql.append(" COUNT(DISTINCT t.id_ticket) AS totalEscaneos,");
		sql.append(" COUNT( hb.producto_id_producto) AS totalProductos,");
		sql.append(" SUM( p.cantidad_bonificacion) AS totalBonificaciones");
		sql.append(" FROM ticket t");
		sql.append(" LEFT JOIN historico_bonificaciones hb ON t.id_ticket = hb.id_ticket");
		sql.append(" LEFT JOIN producto p ON hb.producto_id_producto = p.id_producto");
//		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" GROUP BY topico");
		sql.append(" ORDER BY topico ASC");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).setResultTransformer( (Transformers.aliasToBean(ResumenItem.class)) );
//		q.setParameter("year", year);
		
		List<ResumenItem> list = q.list();
		
		return list;
	}
}
