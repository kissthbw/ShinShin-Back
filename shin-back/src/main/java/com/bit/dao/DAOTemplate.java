
package com.bit.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * En esta clase de definen las operaciones CRUD genericas para las clases DAO
 * @author juanosorioalvarez
 *
 * @param <T> Clase anotada como @Entity
 * @param <PK> Tipo de dato de PK de la clase @Entity
 */
public abstract class DAOTemplate<T, PK extends Serializable> implements Serializable {

	private static final long serialVersionUID = 2408602227263904823L;

	@Autowired
	private SessionFactory sessionFactory;
	
	private final Class<T> persistenClass;
	
	@SuppressWarnings("unchecked")
	public DAOTemplate() {
		this.persistenClass = 
				(Class<T>)( (ParameterizedType) getClass().
						getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/*
	 * CRUD Methods
	 */
	public T save(T entity) {
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}
	
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	public T update(T entity) {
		sessionFactory.getCurrentSession().merge(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public T findByPK(PK pk) {
		return (T) sessionFactory.getCurrentSession().get(persistenClass, pk);
	}
}
