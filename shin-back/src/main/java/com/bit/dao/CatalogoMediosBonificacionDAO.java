package com.bit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.bit.model.CatalogoMediosBonificacion;

@Repository
public class CatalogoMediosBonificacionDAO extends DAOTemplate<CatalogoMediosBonificacion, Long> {

	private static final long serialVersionUID = 3628640656482549065L;
	
	public enum MedioBonificacionID{
		BANCARIA(1, "BA"), PAYPAL(2, "PP"), RECARGA(3, "RT"), DESCONOCIDO(0, "");
		private Integer id;
		private String code;
		
		MedioBonificacionID( int id, String code ) {
			this.id = id;
			this.code = code;
		}
		
		public int value() {
			return id;
		}
		
		public String code() {
			return code;
		}
		
		public static String getById(int id) {
		    for(MedioBonificacionID e : values()) {
		        if(e.id.equals(id)) return e.code();
		    }
		    return DESCONOCIDO.code();
		}
	}

	public List<CatalogoMediosBonificacion> getCatalogoMediosBonificacion() {
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(CatalogoMediosBonificacion.class);
		c.setMaxResults(50);
		c.addOrder(Property.forName("idCatalogoMedioBonificacion").desc());

		return c.list();

	}
	
	public static void main(String[] args) {
		MedioBonificacionID BANCO = MedioBonificacionID.BANCARIA;
		
		System.out.println( BANCO.id );
	}
}
