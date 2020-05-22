package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
import org.jfree.util.Log;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTienda;
import com.bit.model.dto.Item;
import com.bit.model.dto.ResumenItem;
import com.bit.model.report.CatalogoTiendaCSV;

@Repository
public class CatalogoTiendaDAO extends DAOTemplate<CatalogoTienda, Long> {

	private static final long serialVersionUID = -3591680225533837181L;

	public List<CatalogoTienda> getCatalogoTienda() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoTienda.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idCatalogoTienda").desc());
		c.add(Property.forName("active").eq(1));
		return c.list();
	}

	public BigInteger getProducts(Long productos) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(""
				+ "SELECT COUNT(*) FROM catalogo_tienda a left join producto b on a.id_catalogo_tienda=b.id_catalogo_tienda where a.id_catalogo_tienda="
				+ productos + " and b.active=1 group by a.id_catalogo_tienda ;");
		BigInteger total = (BigInteger) q.uniqueResult();

		return total;
	}

	// metodo correspondiente a estadisticas-general
	// metodo que obtiene total escaneos por tienda al mes
	public List<Object> obtieneTotalEscaneosTiendaPorMes() {
		SQLQuery q = getSessionFactory().getCurrentSession()
				.createSQLQuery("" + "SELECT COUNT(*) AS totaltickets,\r\n" + "(nombre_tienda) AS tienda,\r\n"
						+ "MONTHNAME(fecha) AS mes,\r\n" + "YEAR(fecha) AS anio\r\n" + "FROM ticket\r\n"
						+ "GROUP BY anio, fecha, tienda;");
		List<Object> total = (List<Object>) q.list();

		return total;
	}
	
	public List<Item> obtieneTotalEscaneosPorTiendaPorDiaMesAnio( int year, int month) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS total,\r\n" + 
				" (nombre_tienda) AS topico,\r\n" + 
				" DAY(fecha) AS indice\r\n" +  
				" FROM ticket\r\n" + 
				" WHERE YEAR(fecha) = :year" +
				" AND  MONTH(fecha) = :month"+
				" GROUP BY fecha, topico " + 
				" ORDER BY topico, indice").setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		
		return total;
	}

	public List<Item> obtieneTotalEscaneosPorTiendaPorSemanaMesAnio( int year, int month ) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS total,\r\n" + 
				" (nombre_tienda) AS topico,\r\n" + 
				" WEEK(fecha) AS indice\r\n" +  
				" FROM ticket\r\n" + 
				" WHERE YEAR(fecha) = :year" +
				" AND  MONTH(fecha) = :month"+
				" GROUP BY fecha, topico " + 
				" ORDER BY topico, indice").setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		
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

	public List<Item> obtieneTotalEscaneosPorUsuarioTiendaMesAnio(long idUsuario, int year, String tienda) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("	COUNT(t.id_ticket) AS total, ");
		sql.append("	(t.nombre_tienda) AS topico, ");
		sql.append("	MONTH(t.fecha) AS indice  ");
		sql.append(" FROM ticket t,");
		sql.append("	historico_tickets ht");
		sql.append(" WHERE ");
		sql.append(" t.id_ticket = ht.ticket_id_ticket");
		sql.append(" AND YEAR(t.fecha) = :year");
		sql.append(" AND t.nombre_tienda = :tienda");
		sql.append(" AND ht.usuario_id_usuario = :idUsuario");
		sql.append(" GROUP BY indice, topico");
		sql.append(" ORDER BY topico, indice");

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer((Transformers.aliasToBean(Item.class)));
		q.setParameter("idUsuario", idUsuario);
		q.setParameter("year", year);
		q.setParameter("tienda", tienda);

		List<Item> total = q.list();

		return total;
	}

	// Obtiene resumen por tienda
	public List<ResumenItem> obtieneResumenTiendas() {
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

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer((Transformers.aliasToBean(ResumenItem.class)));
//		q.setParameter("year", year);

		List<ResumenItem> list = q.list();

		return list;
	}

	public int eliminaTienda(CatalogoTienda item) {
		Log.info("Entrando eliminaTienda");
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE catalogo_tienda ");
		sql.append(" SET active = 0 ");
		sql.append(" WHERE id_catalogo_tienda = :id ");
		Query q = getSessionFactory().getCurrentSession().createQuery(sql.toString());
		q.setParameter("id", item.getIdCatalogoTienda());
		int rows = q.executeUpdate();

		return rows;
	}

	/**
	 * La data con todas los productos de las marcas en un departamento en las
	 * tiendas
	 * 
	 * @param idTienda opcional. null si se quieren todas las tiendas.
	 * @return
	 */
	public List<CatalogoTiendaCSV> obtieneCatalogoTiendas(String idTienda) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ct.id_catalogo_tienda AS idT, ct.nombre_tienda AS tienda,");
		sql.append("        cm.nombre_marca as marca,");
		sql.append("        p.id_producto AS idP,");
		sql.append("        p.nombre_producto AS producto,");
		sql.append("        p.contenido AS contenido, ");
		sql.append("        ctp.nombre_tipo_producto AS departamento, ");
		sql.append("		CASE  ");
		sql.append("			WHEN p.banner=1 THEN 'General' ");
		sql.append("			WHEN p.banner=1 THEN 'Popular' ");
		sql.append("			WHEN p.banner=1 THEN 'Principal' ");
		sql.append("			ELSE 'Sin especificar' ");
		sql.append("		END AS tipo ");
		sql.append("FROM producto p ");
		sql.append("	INNER JOIN productos_tiendas pt on pt.id_producto=p.id_producto ");
		sql.append("	INNER JOIN catalogo_tienda ct on ct.id_catalogo_tienda=pt.id_catalogo_tienda ");
		sql.append("	INNER JOIN catalogo_marca cm on cm.id_catalogo_marca=p.id_catalogo_marca ");
		sql.append( "	INNER JOIN catalogo_tipo_producto ctp ON ctp.id_catalogo_tipo_producto=p.id_catalogo_tipo_producto ");
		sql.append("WHERE ct.active=1 ");
		if(idTienda != null) {
			sql.append("	AND ct.id_catalogo_tienda=:idT ");
		}
		sql.append("ORDER BY ct.nombre_tienda, cm.nombre_marca, p.nombre_producto, p.contenido ASC; ");

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
//		.addScalar("usuario", StandardBasicTypes.STRING)
//		.addScalar("company", StandardBasicTypes.STRING)
//		.addScalar("numero", StandardBasicTypes.STRING)
//		.addScalar("importe", StandardBasicTypes.DOUBLE)
//		.addScalar("fecha", StandardBasicTypes.DATE)
//		.addScalar("hora", StandardBasicTypes.DATE)
		if(idTienda != null) {
			q.setParameter("idT", idTienda);
		}
		q.setResultTransformer(Transformers.aliasToBean(CatalogoTiendaCSV.class));

		List<CatalogoTiendaCSV> total = q.list();

		return total;
	}

	/**
	 * La data con el todal de bonificaciones que tiene un producto
	 * 
	 * @return
	 */
	public Integer obtieneTotalBonificacionProducto(int idTienda, int idProcucto) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT  ");
		sql.append("     SUM(p.cantidad_bonificacion) as bonificacion ");
		sql.append("FROM producto p ");
		sql.append("INNER JOIN historico_bonificaciones hb ON hb.producto_id_producto=p.id_producto ");
		sql.append("INNER JOIN productos_tiendas pt on pt.id_producto=hb.producto_id_producto ");
		sql.append("INNER JOIN catalogo_tienda ct ON ct.id_catalogo_tienda=pt.id_catalogo_tienda ");
		sql.append("where ct.id_catalogo_tienda=:idT and pt.id_producto=:idP ");
		sql.append("GROUP BY 	ct.id_catalogo_tienda, 	pt.id_producto; ");

		Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		q.setParameter("idT", idTienda);
		q.setParameter("idP", idProcucto);

		Integer total = q.uniqueResult() != null ? ((Double) q.uniqueResult()).intValue() : 0;

		return total;
	}
}
