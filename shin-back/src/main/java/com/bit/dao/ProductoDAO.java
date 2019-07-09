package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bit.model.Producto;

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
		c.setMaxResults(50);
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

}
