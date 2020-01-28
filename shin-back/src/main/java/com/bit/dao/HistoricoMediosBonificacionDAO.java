package com.bit.dao;

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
	public List<BonificacionItem> obtieneDetalleHistoricoBonificaciones( BonificacionItem item ){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append("     c.nombre_medio_bonificacion AS tipo,");
		sql.append("     h.fecha_bonificacion AS fecha,");
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
}
