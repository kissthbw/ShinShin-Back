package com.bit.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.Item;

@Repository
public class HistoricoMediosBonificacionDAO extends DAOTemplate<HistoricoMediosBonificacion, Long> {

	private static final long serialVersionUID = -5802449578063521750L;

	@SuppressWarnings("unchecked")
	public List<HistoricoMediosBonificacion> getHistoricosMediosBonificacion() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(HistoricoMediosBonificacion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idHistoricoMediosBonificacion").desc());

		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<HistoricoMediosBonificacion> getHistoricosMediosBonificacionPorUsuario(Usuario item) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(HistoricoMediosBonificacion.class);
		c.setMaxResults(50);
		c.createAlias("usuario", "user");
		c.add(Restrictions.eq("user.idUsuario", item.getIdUsuario()));
		c.addOrder(Property.forName("idHistoricoMediosBonificacion").desc());

		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<BonificacionItem> obtieneHistoricoBonificaciones(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("     c.nombre_medio_bonificacion AS tipo,");
		sql.append("     h.fecha_bonificacion AS fecha,");
		sql.append("     SUM(h.cantidad_bonificacion) AS importe,");
		sql.append("     COUNT(h.id_historico_medios_bonificacion) AS solicitudes");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append(" 	INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append(" 	INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" GROUP BY tipo, fecha;");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(BonificacionItem.class)) );
		
		List<BonificacionItem> list = q.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<BonificacionItem> obtieneHistoricoBonificacionesPorTipo( Integer[] tipos ){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("     c.nombre_medio_bonificacion AS tipo,");
		sql.append("     h.fecha_bonificacion AS fecha,");
		sql.append("     SUM(h.cantidad_bonificacion) AS importe,");
		sql.append("     COUNT(h.id_historico_medios_bonificacion) AS solicitudes");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append(" 	INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append(" 	INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" 	AND c.id_catalogo_medio_bonificacion IN (:tipos)");
		sql.append(" GROUP BY tipo, fecha;");

		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(BonificacionItem.class)) );
		q.setParameterList("tipos", tipos);
		
		List<BonificacionItem> list = q.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<BonificacionItem> obtieneDetalleHistoricoBonificaciones( BonificacionItem item ){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("     c.nombre_medio_bonificacion AS tipo,");
		sql.append("     h.fecha_bonificacion AS fecha,");
		sql.append("     h.hora_bonificacion AS hora,");
		sql.append("     h.cantidad_bonificacion AS importe,");
//		sql.append("     h.id_historico_medios_bonificacion AS id,");
		sql.append("     h.usuario_id_usuario AS idUsuario");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append(" 	INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append(" 	INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE h.fecha_bonificacion = :year");
		sql.append(" AND c.id_catalogo_medio_bonificacion = :idCatalogo");
		sql.append(" ORDER BY h.id_historico_medios_bonificacion DESC");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(BonificacionItem.class)) );
		q.setParameter("year", item.getFechaFormateada());
		q.setParameter("idCatalogo", item.getIdTipo());
		
		List<BonificacionItem> list = q.list();
		
		return list;
	}
	
	/*
	 * Metodos relacionados a la pagina bonificaciones-general
	 */
	public BigInteger obtieneTotalDepositos(){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(h.id_historico_medios_bonificacion) AS total");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append("     c.id_catalogo_medio_bonificacion IN (1,2)");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		BigInteger total = (BigInteger) q.uniqueResult();
		
		return total;
	}
	
	public BigInteger obtieneTotalRecargas(){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(h.id_historico_medios_bonificacion) AS total");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append("     c.id_catalogo_medio_bonificacion IN (3)");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		BigInteger total = (BigInteger) q.uniqueResult();
		
		return total;
	}
	
	public Double obtieneTotalBonificaciones(){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("     SUM(h.cantidad_bonificacion) AS Bonificaciones");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append(" 	INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append(" 	INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		Double total = (Double) q.uniqueResult();
		
		return total;
	}
	
	public List<Item> obtieneTotalBonificacionesPorTipoDiaMesAnio( int year, int month, Integer[] tipos ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(h.id_historico_medios_bonificacion) AS total,");
		sql.append("     DAY(h.fecha_bonificacion) AS indice");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append(" 	AND month(h.fecha_bonificacion) = :month");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (:tipos)");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameterList("tipos", tipos);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneTotalBonificacionesPorTipoSemanaMesAnio( int year, int month, Integer[] tipos ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(h.id_historico_medios_bonificacion) AS total,");
		sql.append("     WEEK(h.fecha_bonificacion) AS indice");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append(" 	AND month(h.fecha_bonificacion) = :month");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (:tipos)");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameterList("tipos", tipos);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneTotalBonificacionesPorTipoMesAnio( int year, Integer[] tipos ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(h.id_historico_medios_bonificacion) AS total,");
		sql.append("     MONTH(h.fecha_bonificacion) AS indice");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (:tipos)");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameterList("tipos", tipos);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneBonificacionesPorTipoDiaMesAnio( int year, int month, Integer[] tipos ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append("    SUM(h.cantidad_bonificacion) as importe,");
		sql.append("    DAY(h.fecha_bonificacion) AS indice");
		sql.append(" FROM ");
		sql.append("	historico_medios_bonificacion h");
		sql.append("    INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("    INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append("	YEAR(h.fecha_bonificacion) = :year");
		sql.append("	AND month(h.fecha_bonificacion) = :month");
		sql.append("    AND c.id_catalogo_medio_bonificacion IN (:tipos)");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameterList("tipos", tipos);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneBonificacionesPorTipoSemanaMesAnio( int year, int month, Integer[] tipos ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append("     SUM(h.cantidad_bonificacion) as importe,");
		sql.append("     WEEK(h.fecha_bonificacion) AS indice");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append(" 	AND month(h.fecha_bonificacion) = :month");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (:tipos)");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameterList("tipos", tipos);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneBonificacionesPorTipoMesAnio( int year, Integer[] tipos ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append("     SUM(h.cantidad_bonificacion) as importe,");
		sql.append("     MONTH(h.fecha_bonificacion) AS indice");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (:tipos)");
		sql.append(" GROUP BY indice");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameterList("tipos", tipos);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneRecargasPorCompaniaDiaMesAnio( int year, int month, String compania ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(m.compania_medio_bonificacion) AS total,");
		sql.append("     DAY(h.fecha_bonificacion) AS indice,");
		sql.append("     m.compania_medio_bonificacion AS topico");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append(" 	AND month(h.fecha_bonificacion) = :month");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (3)");
		sql.append("     AND m.compania_medio_bonificacion = :compania");
		sql.append(" GROUP BY indice, topico");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameter("compania", compania);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneRecargasPorCompaniaSemanaMesAnio( int year, int month, String compania ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(m.compania_medio_bonificacion) AS total,");
		sql.append("     WEEK(h.fecha_bonificacion) AS indice,");
		sql.append("     m.compania_medio_bonificacion");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append(" 	AND month(h.fecha_bonificacion) = :month");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (3)");
		sql.append("     AND m.compania_medio_bonificacion = :compania");
		sql.append(" GROUP BY indice, m.compania_medio_bonificacion");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		q.setParameter("compania", compania);
		
		List<Item> list = q.list();
		
		return list;
	}
	
	public List<Item> obtieneRecargasPorCompaniaMesAnio( int year, String compania ){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT");
		sql.append(" 	COUNT(m.compania_medio_bonificacion) AS total,");
		sql.append("     MONTH(h.fecha_bonificacion) AS indice,");
		sql.append("     m.compania_medio_bonificacion");
		sql.append(" FROM ");
		sql.append(" 	historico_medios_bonificacion h");
		sql.append("     INNER JOIN medios_bonificacion m ON m.id_medios_bonificacion = h.id_medios_bonificacion");
		sql.append("     INNER JOIN catalogo_medios_bonificacion c ON c.id_catalogo_medio_bonificacion = m.id_catalogo_medio_bonificacion");
		sql.append(" WHERE ");
		sql.append(" 	YEAR(h.fecha_bonificacion) = :year");
		sql.append("     AND c.id_catalogo_medio_bonificacion IN (3)");
		sql.append("     AND m.compania_medio_bonificacion = :compania");
		sql.append(" GROUP BY indice, m.compania_medio_bonificacion");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() ).
				setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("compania", compania);
		
		List<Item> list = q.list();
		
		return list;
	}
	
}
