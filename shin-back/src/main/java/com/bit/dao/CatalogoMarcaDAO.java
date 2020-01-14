package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;
import com.bit.model.CatalogoMarca;

@Repository
public class CatalogoMarcaDAO extends DAOTemplate<CatalogoMarca, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<CatalogoMarca> getCatalogoMarca() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoMarca.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idCatalogoMarca").desc());

		return c.list();
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
}
