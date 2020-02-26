package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bit.model.Producto;
import com.bit.model.dto.Item;

/**
 * Clase DAO mque extiende de DAOTemplate, si se desean implementar metodos de
 * accesos mas espcificos sobre la entidad Producto deberan agregarse aqui. Para
 * ver el funcionamiento del CRUD de ProductoDAO ver ProdcutoDAOTest
 * 
 * @author juanosorioalvarez
 *
 */

@Repository
public class ProductoDAO extends DAOTemplate<Producto, Long> {

	private static final long serialVersionUID = 3231819366945356865L;

	// Metodos especificos
	public List<Producto> getProductos() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		c.setMaxResults(300);
		c.addOrder(Property.forName("idProducto").desc());
		c.add(Property.forName("active").eq(1));

		return c.list();
	}
	
	
	public Long getTotal() {
		Criteria criteriaCount = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		criteriaCount.setProjection(Projections.rowCount());
		Long count = (Long) criteriaCount.uniqueResult();
		
		return count;
	}
	
	public List<Producto> getProductosPorPaginas( int page, int max ) {
		int firstResult = ( (page - 1) * max );
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		c.setMaxResults( Integer.valueOf(max) );
		c.setFirstResult( firstResult );
		c.addOrder(Property.forName("idProducto").desc());

		return c.list();
	}
	
	public List<Producto> getBanners() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		c.setMaxResults(50);
		c.add( Restrictions.eq("banner", true) );
		c.addOrder(Property.forName("idProducto").desc());

		return c.list();
	}
	
	public List<Producto> getProductosPorMarca() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
//		dc.add(Restrictions.like("nombreProducto", "Laptop"));
		c.createAlias("catalogoMarca", "marca");
		c.add( Restrictions.eq("marca.nombreMarca", "Roku") );
		
		
		return ((Criteria) c).list();
	}

	@Transactional
	public List<Producto> getProductosPorMarca(String item, String i) {
		String marca = item;
		String nombreProducto = i;
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		c.add(Restrictions.like("nombreProducto", nombreProducto));
		c.createAlias("catalogoMarca", "marca");
		c.add(Restrictions.eq("marca.nombreMarca", marca));

		return ((Criteria) c).list();
	}

	@Transactional
	public List<Producto> getProductosPorTipo(String item, String i) {
		String tipoProducto = item;
		String nombreProducto = i;
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		c.add(Restrictions.like("nombreProducto", nombreProducto));
		c.createAlias("catalogoTipoProducto", "tipoProducto");
		c.add(Restrictions.eq("tipoProducto.nombreTipoProducto", tipoProducto));

		return ((Criteria) c).list();
	}

	@Transactional
	public List<Producto> getProductosPorNombre(String i) {
		String tipoProducto = i;
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		c.add(Restrictions.like("nombreProducto", tipoProducto));

		return ((Criteria) c).list();
	}
	
	@Transactional
	public List<Producto> getProductosPorIDYEmpresa(List<String> items) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Producto.class);
		c.add(Restrictions.in("nombreProducto", items));

		return ((Criteria) c).list();
	}
	
	//metodo para estadisticas general
	//metodo para obtener total escaneos por producto
	public BigInteger obtieneTotalEscaneosProductos() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(h.producto_id_producto) AS productos\r\n" + 
				"FROM ticket t,\r\n" + 
				"historico_bonificaciones h\r\n" + 
				"WHERE t.id_ticket = h.id_ticket;");
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//metodo para estadisticas general
	//metodo para obtener total escaneos por producto por dia
	public List<Object> obtieneTotalEscaneosProductosPorDia() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(h.producto_id_producto) AS productos,\r\n" + 
				"DAYNAME(t.fecha) AS dia,\r\n" + 
				"fecha\r\n" + 
				"FROM ticket t,\r\n" + 
				"historico_bonificaciones h\r\n" + 
				"WHERE t.id_ticket = h.id_ticket\r\n" + 
				"GROUP BY fecha;");
		List<Object> total = (List<Object>)q.list();
			
		return total;
	}
	
	public List<Item> obtieneTotalEscaneosProductosPorDiaMesAnio( int year, int month ) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(h.producto_id_producto) AS total,\r\n" + 
				"DAY(t.fecha) AS indice\r\n" + 
				"FROM ticket t,\r\n" + 
				"historico_bonificaciones h\r\n" + 
				"WHERE t.id_ticket = h.id_ticket\r\n" +
				" AND YEAR(t.fecha) = :year" +
				" AND month(t.fecha) = :month" +
				" GROUP BY fecha").setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		List<Item> total = q.list();
			
		return total;
	}
	
	//metodo para estadisticas general
	//metodo para obtener total escaneos por producto por semana
	public List<Object> obtieneTotalEscaneosProductosPorSemana() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(h.producto_id_producto) AS productos,\r\n" + 
				"WEEK(t.fecha) AS semana,\r\n" + 
				"YEAR(t.fecha) AS anio\r\n" + 
				"FROM ticket t,\r\n" + 
				"historico_bonificaciones h\r\n" + 
				"WHERE t.id_ticket = h.id_ticket\r\n" + 
				"GROUP BY fecha;");
		List<Object> total = (List<Object>)q.list();
			
		return total;
	}
	
	public List<Item> obtieneTotalEscaneosProductosPorSemanaMesAnio( int year, int month ) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(h.producto_id_producto) AS total,\r\n" + 
				"WEEK(t.fecha) AS indice\r\n" +  
				"FROM ticket t,\r\n" + 
				"historico_bonificaciones h\r\n" + 
				"WHERE t.id_ticket = h.id_ticket\r\n" + 
				" AND YEAR(t.fecha) = :year" +
				" AND month(t.fecha) = :month" +
				" GROUP BY fecha;").setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		List<Item> total = q.list();
			
		return total;
	}
	
	//metodo para estadisticas general
	//metodo para obtener total escaneos por producto por mes
	public List<Object> obtieneTotalEscaneosProductosPorMes() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(h.producto_id_producto) AS productos,\r\n" + 
				"MONTHNAME(t.fecha) AS mes,\r\n" + 
				"YEAR(t.fecha) AS year\r\n" + 
				"FROM ticket t,\r\n" + 
				"historico_bonificaciones h\r\n" + 
				"WHERE t.id_ticket = h.id_ticket\r\n" + 
				"GROUP BY fecha;");
		List<Object> total = (List<Object>)q.list();
			
		return total;
	}
	
	public List<Item> obtieneTotalEscaneosProductosPorMesAnio( int year, int month ) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(h.producto_id_producto) AS total,\r\n" + 
				"MONTH(t.fecha) AS indice\r\n" + 
				"FROM ticket t,\r\n" + 
				"historico_bonificaciones h\r\n" + 
				"WHERE t.id_ticket = h.id_ticket\r\n" + 
				" AND YEAR(t.fecha) = :year" +
				" AND month(t.fecha) = :month" +
				" GROUP BY fecha;").setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		List<Item> total = q.list();
			
		return total;
	}
}
