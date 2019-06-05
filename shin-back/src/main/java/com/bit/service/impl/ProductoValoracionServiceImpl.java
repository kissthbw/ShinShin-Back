package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ProductoValoracionDAO;
import com.bit.model.ProductoValoracion;
import com.bit.service.ProductoValoracionService;

@Service
public class ProductoValoracionServiceImpl implements ProductoValoracionService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoValoracionServiceImpl.class);
	
	@Autowired
	private ProductoValoracionDAO productoValorazionDAO;

	@Override
	@Transactional
	public List<ProductoValoracion> getProductosValoracion() {
		
		log.info("Obtebiendo lista de productos valorados");
		
		List<ProductoValoracion> list = productoValorazionDAO.getProductosValoracion();
		return list;
	}

	@Override
	@Transactional
	public void guardarProductosValoracion(ProductoValoracion item) {
		
		log.info("Guardando valoracion de producto");
		
		productoValorazionDAO.save(item);
	}

	@Override
	@Transactional
	public void actualizarProductosValoracion(ProductoValoracion item) {
		
		log.info("Actualizando valoracion de producto");
		
		productoValorazionDAO.update(item);
	}

}
