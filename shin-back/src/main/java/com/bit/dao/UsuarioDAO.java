package com.bit.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bit.model.Usuario;

@Repository
public class UsuarioDAO extends DAOTemplate<Usuario, Long> {

	private static final long serialVersionUID = 5303350054054307313L;

	public List<Usuario> getUsuarios() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Usuario.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idUsuario").desc());
		return c.list();
	}
	
	public List<Long> getTicketsPorUsuario(Usuario item) {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT ticket_id_ticket FROM historico_tickets WHERE usuario_id_usuario = :idUsuario");
		q.setParameter("idUsuario", item.getIdUsuario());
		q.setFirstResult(0);
		q.setMaxResults(50);
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
	
	public List<Object> obtieneRangoEdadUsuarios() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS Usuarios,\r\n" + 
				"CASE\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) < 18 THEN '-18'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 18 AND 24 THEN '18-24'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 25 AND 29 THEN '25-29'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 30 AND 39 THEN '30-39'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 40 AND 49 THEN '40-49'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) BETWEEN 50 AND 59 THEN '50-59'\r\n" + 
				"WHEN TIMESTAMPDIFF(YEAR, fecha_nac, CURDATE()) > 60 THEN '+60'\r\n" + 
				"END AS RangoEdad\r\n" + 
				"FROM usuario\r\n" + 
				"GROUP BY RangoEdad;");
		
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	public List<Object> obtieneGeneroUsuarios() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT nombre_sexo AS genero, COUNT(*)\r\n" + 
				"FROM usuario JOIN catalogo_sexo\r\n" + 
				"WHERE usuario.id_catalogo_sexo = catalogo_sexo.id_catalogo_sexo\r\n" + 
				"GROUP BY genero;");
		
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	public List<Object> obtieneUsuariosPorMes() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS usuarios,\r\n" + 
				"MONTHNAME(fecha_registro) AS mes,\r\n" + 
				"YEAR(fecha_registro) AS año\r\n" + 
				"FROM usuario\r\n" + 
				"GROUP BY mes\r\n" + 
				"ORDER BY año;");
		
		List<Object> total = (List<Object>)q.list();
		
		return total;
	}
	
	public List<Object> obtieneUsuariosPorSemana() {
		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery("" + "SELECT COUNT(*) AS usuarios,\r\n" + 
				"WEEK(fecha_registro) AS semana,\r\n" + 
				"YEAR(fecha_registro) AS año\r\n" + 
				"FROM usuario\r\n" + 
				"Group BY año, semana;");
		
		List<Object> total = (List<Object>)q.list();
		
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
}
