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
		BANCARIA(1), PAYPAL(2), RECARGA(3);
		private int id;
		
		MedioBonificacionID( int id ) {
			this.id = id;
		}
		
		public int value() {
			return id;
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
