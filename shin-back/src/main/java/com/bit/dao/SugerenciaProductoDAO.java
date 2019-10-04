package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.SugerenciaProducto;

/**
 * Clase DAO mque extiende de DAOTemplate, si se desean implementar metodos de
 * accesos mas espcificos sobre la entidad Producto deberan agregarse aqui. Para
 * ver el funcionamiento del CRUD de ProductoDAO ver ProdcutoDAOTest
 * 
 * @author juanosorioalvarez
 *
 */

@Repository
public class SugerenciaProductoDAO extends DAOTemplate<SugerenciaProducto, Long> {

	private static final long serialVersionUID = 3231819366945356865L;

	// Metodos especificos
	public List<SugerenciaProducto> getSugerenciaProductos() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(SugerenciaProducto.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("id").desc());

		return c.list();
	}
}
