package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bit.model.Producto;
import com.bit.model.ProductosTiendas;

@Repository
public class ProductosTiendasDAO extends DAOTemplate<ProductosTiendas, Long> {

	private static final long serialVersionUID = -383483471996532835L;

	public List<ProductosTiendas> getProductosTiendas() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductosTiendas.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idProductoTienda").desc());

		return c.list();
	}
	
	@Transactional
	public List<ProductosTiendas> getProductosPorIDYEmpresa(List<String> items) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductosTiendas.class);
		c.add(Restrictions.in("productoTienda", items));

		return ((Criteria) c).list();
	}
	
	@Transactional
	public List<ProductosTiendas> getProductosPorIDTienda(Integer idTienda) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductosTiendas.class);
		c.createAlias("catalogoTienda", "c");
		
		if( null != idTienda ) {
			c.add(Restrictions.eq("c.idCatalogoTienda", idTienda));
		}
		c.add( Restrictions.ne("productoTienda", "") );

		return ((Criteria) c).list();
	}
	
	public List<ProductosTiendas> getTiendasPorProducto( Long idProducto ) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(ProductosTiendas.class);
		c.createAlias("producto", "p");
		c.add( Restrictions.eq("p.idProducto", idProducto) );

		return c.list();
	}
}
