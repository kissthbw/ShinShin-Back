package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.bit.model.Producto;
import com.bit.model.Proveedor;
import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.Item;
import com.bit.model.dto.ProductoItem;

@Repository
public class ProveedorDAO extends DAOTemplate<Proveedor, Long> {

	private static final long serialVersionUID = -2948368451152236181L;

	public List<Proveedor> getProveedores() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Proveedor.class);
		c.add( Restrictions.eq("active", true) );
		c.setMaxResults(200);
		c.addOrder(Order.desc("id"));
		
		return c.list();
	}
	
	public Proveedor finfByEmail( String email ) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Proveedor.class);
		c.add( Restrictions.eq("email", email) );
		c.setMaxResults(200);
		c.addOrder(Order.desc("id"));
		
		return (Proveedor)c.uniqueResult();
	}
	
	public int deleteById( Long id ) {
		StringBuilder sql = new StringBuilder();
		sql.append( " UPDATE proveedor SET active = 0" );
		sql.append( " WHERE id = :id" );
		
		Query query = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		query.setParameter("id", id);
		
		return query.executeUpdate();
		
	}
	
	//Metodos relacionados con las marcas
	public Double obtieneTotalBonificacionesPorMarca( Long idMarca ) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("   SUM(p.cantidad_bonificacion)");
		sql.append(" FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE p.id_catalogo_marca = :idMarca");
//		sql.append(" AND YEAR(t.fecha) = 2020");
		
		Query query = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		query.setParameter("idMarca", idMarca);
		
		Double total = (Double)query.uniqueResult();
		
		return total;
	}
	
	public BigInteger obtieneTotalProductosEscaneadosPorMarca( Long idMarca ) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("   COUNT(hb.producto_id_producto)");
		sql.append(" FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE p.id_catalogo_marca = :idMarca");
		
		Query query = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		query.setParameter("idMarca", idMarca);
		
		BigInteger total = (BigInteger)query.uniqueResult();
		
		return total;
	}
	
	public List<Item> obtieneTotalEscaneosMarcaProductosPorDiaMesAnio( int year, int month, long idMarca ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 	COUNT(hb.producto_id_producto) AS total, ");
		sql.append(" 	DAY(t.fecha) AS indice ");
		sql.append(" FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND month(t.fecha) = :month ");
		sql.append(" AND p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery( sql.toString() )
				.setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameter("idMarca", idMarca);
		List<Item> total = q.list();
			
		return total;
	}
	
	public List<Item> obtieneTotalEscaneosMarcaProductosPorSemanaMesAnio( int year, int month, long idMarca ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 	COUNT(hb.producto_id_producto) AS total,");
		sql.append("     WEEK(t.fecha) AS indice ");
		sql.append("     FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND month(t.fecha) = :month");
		sql.append(" AND p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery( sql.toString() )
				.setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameter("idMarca", idMarca);
		List<Item> total = q.list();
			
		return total;
	}
	
	public List<Item> obtieneTotalEscaneosMarcaProductosPorMesAnio( int year, long idMarca ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 	COUNT(hb.producto_id_producto) AS total,");
		sql.append("     MONTH(t.fecha) AS indice ");
		sql.append("     FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery( sql.toString() )
				.setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("idMarca", idMarca);
		
		List<Item> total = q.list();
			
		return total;
	}
	
	/*
	 * Bonificaciones
	 */
	public List<Item> obtieneTotalBonificacionesMarcaProductosPorDiaMesAnio( int year, int month, long idMarca ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 	SUM(p.cantidad_bonificacion) AS importe, ");
		sql.append(" 	DAY(t.fecha) AS indice ");
		sql.append(" FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND month(t.fecha) = :month ");
		sql.append(" AND p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery( sql.toString() )
				.setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameter("idMarca", idMarca);
		List<Item> total = q.list();
			
		return total;
	}
	
	public List<Item> obtieneTotalBonificacionesMarcaProductosPorSemanaMesAnio( int year, int month, long idMarca ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 	SUM(p.cantidad_bonificacion) AS importe,");
		sql.append("     WEEK(t.fecha) AS indice ");
		sql.append("     FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND month(t.fecha) = :month");
		sql.append(" AND p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery( sql.toString() )
				.setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameter("idMarca", idMarca);
		List<Item> total = q.list();
			
		return total;
	}
	
	public List<Item> obtieneTotalBonificacionesMarcaProductosPorMesAnio( int year, long idMarca ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" 	SUM(p.cantidad_bonificacion) AS importe,");
		sql.append("     MONTH(t.fecha) AS indice ");
		sql.append("     FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE YEAR(t.fecha) = :year");
		sql.append(" AND p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery( sql.toString() )
				.setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("idMarca", idMarca);
		
		List<Item> total = q.list();
			
		return total;
	}
	
	public List<ProductoItem> obtieneInfoReporteEmpresaGeneral( long idMarca ) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" p.id_producto AS id,");
		sql.append(" p.nombre_producto AS nombre, ");
		sql.append(" p.cantidad_bonificacion AS bonificacion");
		sql.append(" FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		// sql.append(" WHERE YEAR(t.fecha) = 2020");
		sql.append(" AND p.id_catalogo_marca = :idMarca");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() )
				.addScalar("id", StandardBasicTypes.LONG)
				.addScalar("nombre", StandardBasicTypes.STRING)
				.addScalar("bonificacion", StandardBasicTypes.DOUBLE)
				.setResultTransformer( (Transformers.aliasToBean(ProductoItem.class)) );
		
		q.setParameter("idMarca", idMarca);
		
		List<ProductoItem> list = q.list();
		
		return list;
	}
	
	/*
	 * Dashboard empresa - Finanzas -
	 */
	public List<BonificacionItem> obtieneListaBonificacionesEmpresa( long idMarca ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("     SUM(p.cantidad_bonificacion) AS importe,");
		sql.append("     t.fecha AS fecha");
		sql.append("     FROM historico_bonificaciones hb");
		sql.append(" INNER JOIN ticket t ON t.Id_ticket = hb.id_ticket");
		sql.append(" INNER JOIN producto p ON p.Id_producto = hb.producto_id_producto");
		sql.append(" WHERE p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY fecha");
		sql.append(" ORDER BY fecha DESC;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() )
				.addScalar("importe", StandardBasicTypes.DOUBLE)
				.addScalar("fecha", StandardBasicTypes.DATE)
				.setResultTransformer( (Transformers.aliasToBean(BonificacionItem.class)) );
		
		q.setParameter("idMarca", idMarca);
		
		List<BonificacionItem> list = q.list();
		
		return list;
	}
	
	/*
	 * Dashboard empresa - Productos -
	 */
	public BigInteger obtieneTotalProductosPorMarca( Long idMarca ) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("   COUNT(p.id_producto)");
		sql.append(" FROM producto p");
		sql.append(" WHERE p.id_catalogo_marca = :idMarca");
		
		Query query = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		query.setParameter("idMarca", idMarca);
		
		BigInteger total = (BigInteger)query.uniqueResult();
		
		return total;
	}
	
	public List<Producto> obtieneListaProductosEmpresa( long idMarca ){
		StringBuilder sql = new StringBuilder();
		
		sql.append("  SELECT ");
		sql.append("	p.id_producto AS idProducto,");
		sql.append("	p.nombre_producto AS nombreProducto,");
		sql.append("    COUNT( hb.producto_id_producto ) AS totalEscaneos,");
		sql.append("    ( p.cantidad_bonificacion * COUNT( hb.producto_id_producto ) ) AS totalBonificacion");
		sql.append("  FROM producto p");
		sql.append("  LEFT JOIN historico_bonificaciones hb ON p.id_producto = hb.producto_id_producto");
		sql.append("  WHERE p.id_catalogo_marca = :idMarca");
		sql.append("  GROUP BY idProducto");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() )
				.addScalar("idProducto", StandardBasicTypes.LONG)
				.addScalar("nombreProducto", StandardBasicTypes.STRING)
				.addScalar("totalEscaneos", StandardBasicTypes.LONG)
				.addScalar("totalBonificacion", StandardBasicTypes.DOUBLE)
				.setResultTransformer( (Transformers.aliasToBean(Producto.class)) );
		
		q.setParameter("idMarca", idMarca);
		
		List<Producto> list = q.list();
		
		return list;
	}
	
	/*
	 * Dashboard empresa - Usuarios -
	 */
	public List<Usuario> obtieneInformacionUsuariosEmpresa( long idMarca ){
		StringBuilder sql = new StringBuilder();
		

		sql.append(" SELECT ");
		sql.append(" 	ht.usuario_id_usuario AS idUsuario,");
		sql.append("     u.id_catalogo_sexo AS idCatalogoSexo, ");
		sql.append("     u.nombre AS nombre,");
		sql.append("     TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) AS edad");
		sql.append(" FROM historico_tickets ht");
		sql.append(" INNER JOIN usuario u ON u.id_usuario = ht.usuario_id_usuario");
		sql.append(" INNER JOIN ticket t ON t.id_ticket = ht.ticket_id_ticket");
		sql.append(" INNER JOIN historico_bonificaciones hb ON hb.id_ticket = t.id_ticket");
		sql.append(" INNER JOIN producto p ON p.id_producto = hb.producto_id_producto");
		sql.append(" WHERE p.id_catalogo_marca = :idMarca");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() )
				.addScalar("idUsuario", StandardBasicTypes.LONG)
				.addScalar("idCatalogoSexo", StandardBasicTypes.INTEGER)
				.addScalar("nombre", StandardBasicTypes.STRING)
				.addScalar("edad", StandardBasicTypes.INTEGER)
				.setResultTransformer( (Transformers.aliasToBean(Usuario.class)) );
		
		q.setParameter("idMarca", idMarca);
		
		List<Usuario> list = q.list();
		
		return list;
	}
	
	public List<Usuario> obtieneListaUsuariosEmpresa( long idMarca ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("     ht.usuario_id_usuario AS idUsuario,");
		sql.append("     u.id_catalogo_sexo AS idCatalogoSexo, ");
		sql.append("     cs.nombre_sexo AS sexo, ");
		sql.append("     u.nombre AS nombre,");
		sql.append("     TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) AS edad,");
		sql.append("     COUNT( u.id_usuario ) AS escaneos");
		sql.append(" FROM historico_tickets ht");
		sql.append(" INNER JOIN usuario u ON u.id_usuario = ht.usuario_id_usuario");
		sql.append(" INNER JOIN catalogo_sexo cs ON cs.id_catalogo_sexo = u.id_catalogo_sexo");
		sql.append(" INNER JOIN ticket t ON t.id_ticket = ht.ticket_id_ticket");
		sql.append(" INNER JOIN historico_bonificaciones hb ON hb.id_ticket = t.id_ticket");
		sql.append(" INNER JOIN producto p ON p.id_producto = hb.producto_id_producto");
		sql.append(" WHERE p.id_catalogo_marca = :idMarca");
		sql.append(" GROUP BY idUsuario");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() )
				.addScalar("idUsuario", StandardBasicTypes.LONG)
				.addScalar("idCatalogoSexo", StandardBasicTypes.INTEGER)
				.addScalar("sexo", StandardBasicTypes.STRING)
				.addScalar("nombre", StandardBasicTypes.STRING)
				.addScalar("edad", StandardBasicTypes.INTEGER)
				.addScalar("escaneos", StandardBasicTypes.LONG)
				.setResultTransformer( (Transformers.aliasToBean(Usuario.class)) );
		
		q.setParameter("idMarca", idMarca);
		
		List<Usuario> list = q.list();
		
		return list;
	}
}
