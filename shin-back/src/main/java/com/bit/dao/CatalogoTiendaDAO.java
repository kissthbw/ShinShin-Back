package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoTienda;

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
}
