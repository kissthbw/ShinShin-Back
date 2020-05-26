package com.bit.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.bit.model.Usuario;
import com.bit.model.dto.Item;
import com.bit.model.report.ProductoTicketUsuarioReport;
import com.bit.model.report.RetirosUsuarioReport;

@Repository
public class UsuarioDAO extends DAOTemplate<Usuario, Long> {

	private static final long serialVersionUID = 5303350054054307313L;

	public List<Usuario> getUsuarios() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idUsuario").desc());
		return c.list();
	}
	
	public List<Long> getTicketsPorUsuario(Usuario item, Integer maxResults) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT ticket_id_ticket FROM historico_tickets WHERE usuario_id_usuario = :idUsuario");
		q.setParameter("idUsuario", item.getIdUsuario());
		q.setFirstResult(0);
		
		if( null != maxResults ) {
			q.setMaxResults(maxResults);
		}
		
		List<Object> list = q.list();
		List<Long> tmpList = new ArrayList<>();
		
		for( Object o : list ) {
			tmpList.add( Long.parseLong(o.toString()) );
		}
		
		
		return tmpList ;
	}
	
	public Usuario findUserByUser(String usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.like("usuario", usuario));

		return (Usuario)c.uniqueResult();
	}
	
	public Usuario findUserByUserOrPhone(Usuario usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		
		if( null != usuario.getUsuario() ) {
			c.add(Restrictions.eq("usuario", usuario.getUsuario()));
		}
		
		if( null != usuario.getTelMovil() ) {
			c.add(Restrictions.eq("telMovil", usuario.getTelMovil()));
		}

		return (Usuario)c.uniqueResult();
	}
	
	public boolean existUserByUserOrPhone(Usuario usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		Criterion u = Restrictions.eq("usuario", usuario.getUsuario()) ;
		Criterion t = Restrictions.eq("telMovil", usuario.getTelMovil()) ;
		
		LogicalExpression or = Restrictions.or(u, t);
		c.add(or);
		List<Usuario> list = c.list();
		
		if( list.isEmpty() ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean existUserByUser(Usuario usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		Criterion u = Restrictions.eq("usuario", usuario.getUsuario()) ;
		c.add(u);
		List<Usuario> list = c.list();
		
		if( list.isEmpty() ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean existUserByEmail(Usuario usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		Criterion u = Restrictions.eq("correoElectronico", usuario.getCorreoElectronico()) ;
		c.add(u);
		List<Usuario> list = c.list();
		
		if( list.isEmpty() ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean existUserByPhone(Usuario usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		Criterion t = Restrictions.eq("telMovil", usuario.getTelMovil()) ;
		c.add(t);
		List<Usuario> list = c.list();
		
		if( list.isEmpty() ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public List<Usuario> findUserByUser2(String usuario) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.like("usuario", usuario));

		return c.list();
	}
	
	public List<Usuario> findUserByPhone(String tel) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("telMovil", tel));
		c.add(Restrictions.not( Restrictions.eq("estatus", 2) ));
		
		return c.list();
	}
	
	public Usuario findBySocialMediaUser(Usuario item) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.like("usuario", item.getUsuario()));
//		c.add(Restrictions.like("idRedSocial", item.getIdRedSocial()));

		return (Usuario)c.uniqueResult();
	}
	
	public Usuario findUserByUserAndPassword(String usuario, String contrasenia) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("usuario", usuario));
		c.add(Restrictions.eq("contrasenia", contrasenia));
		c.add(Restrictions.not( Restrictions.eq("estatus", 2) ));
		
		return (Usuario) c.uniqueResult();
	}
	
	public Usuario findUserByPhoneAndPassword(String tel, String contrasenia) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("telMovil", tel));
		c.add(Restrictions.eq("contrasenia", contrasenia));
		c.add(Restrictions.not( Restrictions.eq("estatus", 2) ));
		
		return (Usuario) c.uniqueResult();
	}
	
	public Usuario findUserByUserAndHash(String usuario, String hash) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("usuario", usuario));
		c.add(Restrictions.eq("hash", hash));
		c.add(Restrictions.not( Restrictions.eq("estatus", 2) ));
		
		return (Usuario) c.uniqueResult();
	}
	
	public Usuario findUserByEmail(String email) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("correoElectronico", email));
//		c.add(Restrictions.eq("idRedSocial", null));
		c.setMaxResults(1);
		
		return (Usuario) c.uniqueResult();
	}
	
	public Usuario findUserByRestoreLink(Usuario item) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("password_restore_link", item.getPassword_restore_link()));
		c.setMaxResults(1);
		
		return (Usuario) c.uniqueResult();
	}
	
	public Usuario findUserByActivationLink(Usuario item) {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("activation_link", item.getActivation_link()));
		c.setMaxResults(1);
		
		return (Usuario) c.uniqueResult();
	}
	
	//Obtiene los tickets totales del usuario
	public BigInteger calculaTicketsTotales(Usuario item) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(usuario_id_usuario) FROM historico_tickets\n" + 
				" WHERE usuario_id_usuario = :idUsuario");
		q.setParameter("idUsuario", item.getIdUsuario());
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	public BigInteger obtieneTotalProductosPorUsario(Usuario item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(usuario_id_usuario) ");
		sql.append(" FROM historico_tickets");
		sql.append(" WHERE usuario_id_usuario = :idUsuario");
		
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		q.setParameter("idUsuario", item.getIdUsuario());
		
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	//Obtiene las bonificaciones solicitadas por el usuario
	public BigInteger calculaBanoficacionesTotales(Usuario item) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(usuario_id_usuario) FROM historico_medios_bonificacion\n" + 
				" WHERE usuario_id_usuario = :idUsuario");
		q.setParameter("idUsuario", item.getIdUsuario());
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	
	//Obtiene el total de cuentas(medios de bonificacion) del usuario
	public BigInteger calculaMediosBonificacionTotales(Usuario item) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(usuario_id_usuario) FROM medios_bonificacion\n" + 
				" WHERE usuario_id_usuario = :idUsuario");
		q.setParameter("idUsuario", item.getIdUsuario());
		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	public BigInteger obtieneTotalUsuarios() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(""
				+ "SELECT COUNT(*) FROM usuario WHERE estatus = true;");

		BigInteger total = (BigInteger)q.uniqueResult();
		
		return total;
	}
	
	public BigDecimal obtienePromedioEdadUsuarios() {
		SQLQuery q= getSessionFactory().getCurrentSession().createSQLQuery("" 
				+ "SELECT AVG(TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE())) AS EdadPromedio FROM usuario;");
		
		BigDecimal total = (BigDecimal)q.uniqueResult();
		
		return total;
	}
	
	public List<Item> obtieneRangoEdadUsuarios() {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS total,\r\n" + 
				"CASE\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) < 18 THEN '-18'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 18 AND 24 THEN '18-24'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 25 AND 29 THEN '25-29'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 30 AND 39 THEN '30-39'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 40 AND 49 THEN '40-49'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 50 AND 59 THEN '50-59'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) > 60 THEN '+60'\r\n" + 
				"END AS topico\r\n" + 
				"FROM usuario\r\n" + 
				"GROUP BY topico;").setResultTransformer(( Transformers.aliasToBean(Item.class)));
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Item> obtieneGeneroUsuarios() {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT nombre_sexo AS topico, COUNT(*) as total\r\n" + 
				"FROM usuario JOIN catalogo_sexo\r\n" + 
				"WHERE usuario.id_catalogo_sexo = catalogo_sexo.id_catalogo_sexo\r\n" + 
				"GROUP BY topico;").setResultTransformer(( Transformers.aliasToBean(Item.class)));
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Object> obtieneUsuariosPorMes() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS usuarios,\r\n" + 
				"MONTH(fecha_registro) AS mes,\r\n" + 
				"YEAR(fecha_registro) AS year\r\n" + 
				"FROM usuario\r\n" + 
				"GROUP BY mes, year\r\n" + 
				"ORDER BY year;");
		
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	public List<Item> obtieneUsuariosPorMesYAnio(Integer year) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT " + 
				" COUNT(*) AS total, " +
				" MONTH(fecha_registro) AS indice " +  
//				" YEAR(fecha_registro) AS year\r\n" + 
				" FROM usuario\r\n" + 
				" WHERE YEAR(fecha_registro) = :year " +
				" GROUP BY indice \r\n" + 
				" ORDER BY indice ASC").setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Object> obtieneUsuariosPorSemana() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS usuarios,\r\n" + 
				"WEEK(fecha_registro) AS semana,\r\n" + 
				"YEAR(fecha_registro) AS year\r\n" + 
				"FROM usuario\r\n" + 
				"Group BY year, semana;");
		
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	public List<Item> obtieneUsuariosPorSemanaMesAnio( int year, int month ) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(
				"SELECT " +
						"	COUNT(*) AS total," +
						"	WEEK(fecha_registro) AS indice" +
//						"	YEAR(fecha_registro) AS year" +
						" FROM usuario" +
						" WHERE YEAR(fecha_registro) = :year" +
						" AND month(fecha_registro) = :month" +
						" Group BY indice"
		).setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public List<Object> obtieneUsuariosPorDia() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS usuarios,\r\n" + 
				"DAYNAME(fecha_registro) AS dia,\r\n" + 
				"(fecha_registro) AS fecha\r\n" + 
				"FROM usuario\r\n" + 
				"GROUP BY fecha;");
		
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	public List<Item> obtieneUsuariosPorDiaMesAnio( int year, int month ) {
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(
				" SELECT " +
						" 	COUNT(*) AS total," +
						" 	DAY(fecha_registro) AS indice" +
//						" 	(fecha_registro) AS fecha" +
						" FROM usuario" +
						" WHERE YEAR(fecha_registro) = :year" +
						" AND month(fecha_registro) = :month" +
						" GROUP BY indice"
		).setResultTransformer( (Transformers.aliasToBean(Item.class)) );
		q.setParameter("year", year);
		q.setParameter("month", month);
		
		List<Item> total = q.list();
		
		return total;
	}
	
	public String obtieneDeviceTokenPorUsuario(Usuario item) {
		
		StringBuilder sql = new StringBuilder();
		sql.append( " SELECT device_token FROM usuario " );
		sql.append( " WHERE id_usuario = :idUsuario" );
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery( sql.toString() );
		q.setParameter("idUsuario", item.getIdUsuario());
		
		String deviceToken = (String)q.uniqueResult();
		
		return deviceToken;
	}
	
	public List<ProductoTicketUsuarioReport> obtieneProductosTicketUsuarioPorMarca( Long idMarca, Long idUsuario ) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(" u.id_usuario AS idUsuario,");
		sql.append(" u.nombre AS nombre,");
		sql.append(" TIMESTAMPDIFF(YEAR, u.fecha_nac, CURDATE()) AS edad,");
		sql.append(" cs.nombre_sexo AS sexo,");
		sql.append(" u.correo_electronico AS email,");

		sql.append("   (");
		sql.append(" SELECT ");
		sql.append(" 	SUM( if(cmb.id_catalogo_medio_bonificacion = '1',hmb.cantidad_bonificacion,0) ) AS bancaria");
		sql.append(" FROM historico_medios_bonificacion hmb");
		sql.append(" INNER JOIN medios_bonificacion mb ON mb.id_medios_bonificacion = hmb.id_medios_bonificacion");
		sql.append(" INNER JOIN catalogo_medios_bonificacion cmb ON cmb.id_catalogo_medio_bonificacion = mb.id_catalogo_medio_bonificacion");
		sql.append(" WHERE hmb.fecha_bonificacion <= t.fecha");
		sql.append("       AND hmb.usuario_id_usuario = u.id_usuario");
		sql.append(" GROUP by  hmb.usuario_id_usuario");
		sql.append("   ) AS retirosBancarios,");
		sql.append("       (");
		sql.append(" SELECT ");
		sql.append(" 	SUM( if(cmb.id_catalogo_medio_bonificacion = '2',hmb.cantidad_bonificacion,0) ) AS bancaria");
		sql.append(" FROM historico_medios_bonificacion hmb");
		sql.append(" INNER JOIN medios_bonificacion mb ON mb.id_medios_bonificacion = hmb.id_medios_bonificacion");
		sql.append(" INNER JOIN catalogo_medios_bonificacion cmb ON cmb.id_catalogo_medio_bonificacion = mb.id_catalogo_medio_bonificacion");
		sql.append(" WHERE hmb.fecha_bonificacion <= t.fecha");
		sql.append("       AND hmb.usuario_id_usuario = u.id_usuario");
		sql.append(" GROUP by  hmb.usuario_id_usuario");
		sql.append("   ) AS retirosPayPal,");
		sql.append("       (");
		sql.append(" SELECT ");
		sql.append(" 	SUM( if(cmb.id_catalogo_medio_bonificacion = '3',hmb.cantidad_bonificacion,0) ) AS bancaria");
		sql.append(" FROM historico_medios_bonificacion hmb");
		sql.append(" INNER JOIN medios_bonificacion mb ON mb.id_medios_bonificacion = hmb.id_medios_bonificacion");
		sql.append(" INNER JOIN catalogo_medios_bonificacion cmb ON cmb.id_catalogo_medio_bonificacion = mb.id_catalogo_medio_bonificacion");
		sql.append(" WHERE hmb.fecha_bonificacion <= t.fecha");
		sql.append("       AND hmb.usuario_id_usuario = u.id_usuario");
		sql.append(" GROUP by  hmb.usuario_id_usuario");
		sql.append("   ) AS recargas,");
		
		sql.append(" u.tel_movil as telefono, ");
		sql.append(" u.codigo_postal AS cp,");
		sql.append(" u.fecha_registro AS desde,");
		sql.append(" u.id_catalogo_red_social AS via,");
		sql.append(" t.nombre_tienda AS tienda,");
		sql.append(" t.ticket_fecha AS fechaTicket,");
		sql.append(" t.ticket_hora AS horaTicket,");
		sql.append(" t.fecha AS fechaEscaneo,");
		sql.append(" t.hora AS horaEscaneo,");
		sql.append(" t.ticket_cp_fiscal AS cpTicketFiscal,");
		sql.append(" t.ticket_cp_tienda AS cpTicketTienda,");
		sql.append(" p.nombre_producto AS producto,");
		sql.append(" p.contenido AS contenido,");
		sql.append(" cm.nombre_marca AS marca,");
		sql.append(" ctp.nombre_tipo_producto AS depto,");
		sql.append(" p.banner AS tipo,");
		sql.append(" p.cantidad_bonificacion AS bonificacion");
		
		sql.append(" FROM usuario u");
		sql.append(" INNER JOIN catalogo_sexo cs ON cs.id_catalogo_sexo = u.id_catalogo_sexo");
		sql.append(" INNER JOIN historico_tickets ht ON ht.usuario_id_usuario = u.id_usuario");
		sql.append(" INNER JOIN ticket t ON t.id_ticket = ht.ticket_id_ticket");
		sql.append(" INNER JOIN historico_bonificaciones hb ON hb.id_ticket = ht.ticket_id_ticket");
		sql.append(" INNER JOIN producto p ON p.id_producto = hb.producto_id_producto");
		sql.append(" INNER JOIN catalogo_marca cm ON cm.id_catalogo_marca = p.id_catalogo_marca");
		sql.append(" INNER JOIN catalogo_tipo_producto ctp ON ctp.id_catalogo_tipo_producto = p.id_catalogo_tipo_producto");
		
		if( null != idUsuario ) {
			sql.append(" WHERE ht.usuario_id_usuario = :idUsuario");
		}
		
		
		sql.append(" ORDER BY ht.usuario_id_usuario, ht.ticket_id_ticket ASC");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(
				sql.toString()
				)
				.addScalar("idUsuario", StandardBasicTypes.LONG)
				.addScalar("nombre", StandardBasicTypes.STRING)
				.addScalar("edad", StandardBasicTypes.STRING)
				.addScalar("sexo", StandardBasicTypes.STRING)
				.addScalar("email", StandardBasicTypes.STRING)
				.addScalar("retirosBancarios", StandardBasicTypes.STRING)
				.addScalar("retirosPayPal", StandardBasicTypes.STRING)
				.addScalar("recargas", StandardBasicTypes.STRING)
				
				.addScalar("telefono", StandardBasicTypes.STRING)
				.addScalar("cp", StandardBasicTypes.STRING)
				.addScalar("desde", StandardBasicTypes.STRING)
				.addScalar("via", StandardBasicTypes.STRING)
				.addScalar("tienda", StandardBasicTypes.STRING)
				.addScalar("fechaTicket", StandardBasicTypes.STRING)
				.addScalar("horaTicket", StandardBasicTypes.STRING)
				.addScalar("fechaEscaneo", StandardBasicTypes.STRING)
				.addScalar("horaEscaneo", StandardBasicTypes.STRING)
				.addScalar("cpTicketFiscal", StandardBasicTypes.STRING)
				.addScalar("cpTicketTienda", StandardBasicTypes.STRING)
				.addScalar("producto", StandardBasicTypes.STRING)
				.addScalar("contenido", StandardBasicTypes.STRING)
				.addScalar("marca", StandardBasicTypes.STRING)
				.addScalar("depto", StandardBasicTypes.STRING)
				.addScalar("tipo", StandardBasicTypes.STRING)
				.addScalar("bonificacion", StandardBasicTypes.STRING)
				.setResultTransformer( (Transformers.aliasToBean(ProductoTicketUsuarioReport.class)) );
		
		if( null != idUsuario ) {
			q.setParameter("idUsuario", idUsuario);
		}
		
//		q.setParameter("month", month);
		
		List<ProductoTicketUsuarioReport> list = q.list();
		
		return list;
	}
	
	public List<RetirosUsuarioReport> obtieneRetirosUsuarioPorMarca( long idMarca ) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" 	hmb.fecha_bonificacion AS fecha,");
		sql.append(" 	SUM( if(cmb.id_catalogo_medio_bonificacion = '1',hmb.cantidad_bonificacion,0) ) AS retirosBancarios,");
		sql.append("     SUM( if(cmb.id_catalogo_medio_bonificacion = '2',hmb.cantidad_bonificacion,0) ) AS retirosPayPal,");
		sql.append("     SUM( if(cmb.id_catalogo_medio_bonificacion = '3',hmb.cantidad_bonificacion,0) ) AS recargas,");
		sql.append("     hmb.usuario_id_usuario AS idUsuario");
		sql.append(" FROM historico_medios_bonificacion hmb");
		sql.append(" INNER JOIN medios_bonificacion mb ON mb.id_medios_bonificacion = hmb.id_medios_bonificacion");
		sql.append(" INNER JOIN catalogo_medios_bonificacion cmb ON cmb.id_catalogo_medio_bonificacion = mb.id_catalogo_medio_bonificacion");
		sql.append(" GROUP by fecha_bonificacion, hmb.usuario_id_usuario");
		sql.append(" ORDER BY fecha_bonificacion DESC");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(
				sql.toString()
				)
				.addScalar("idUsuario", StandardBasicTypes.LONG)
				.addScalar("fecha", StandardBasicTypes.STRING)
				.addScalar("retirosBancarios", StandardBasicTypes.STRING)
				.addScalar("retirosPayPal", StandardBasicTypes.STRING)
				.addScalar("recargas", StandardBasicTypes.STRING)
				.setResultTransformer( (Transformers.aliasToBean(RetirosUsuarioReport.class)) );
		
		List<RetirosUsuarioReport> list = q.list();
		
		return list;
	}
	
	public List<Long> obtieneUsuariosPorMarca( long idMarca ) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT");
		sql.append("     u.id_usuario AS idUsuario");
		sql.append(" FROM usuario u");
		sql.append(" INNER JOIN catalogo_sexo cs ON cs.id_catalogo_sexo = u.id_catalogo_sexo");
		sql.append(" INNER JOIN historico_tickets ht ON ht.usuario_id_usuario = u.id_usuario");
		sql.append(" INNER JOIN ticket t ON t.id_ticket = ht.ticket_id_ticket");
		sql.append(" INNER JOIN historico_bonificaciones hb ON hb.id_ticket = ht.ticket_id_ticket");
		sql.append(" INNER JOIN producto p ON p.id_producto = hb.producto_id_producto");
		sql.append(" WHERE p.id_catalogo_marca = :idMarca");
		
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(
				sql.toString()
				).addScalar("idUsuario", StandardBasicTypes.LONG);
		q.setParameter("idMarca", idMarca);
		
		List<Long> list = q.list();
		
		return list;
	}
}
