package com.bit.dao;

import org.springframework.stereotype.Repository;

import com.bit.model.Producto;

@Repository
public class ProductoDAO extends DAOTemplate<Producto, Long> {
	
	private static final long serialVersionUID = 3231819366945356865L;
	
}
