package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ProductoValoracionDAO;
import com.bit.model.ProductoValoracion;
import com.bit.service.ProductoValoracionService;

@Service
public class ProductoValoracionServiceImpl implements ProductoValoracionService {

	@Autowired
	private ProductoValoracionDAO productoValorazionDAO;

	@Override
	@Transactional
	public List<ProductoValoracion> getProductosValoracion() {
		List<ProductoValoracion> list = productoValorazionDAO.getProductosValoracion();
		return list;
	}

	@Override
	@Transactional
	public void guardarProductosValoracion(ProductoValoracion item) {
		productoValorazionDAO.save(item);
	}

	@Override
	@Transactional
	public void actualizarProductosValoracion(ProductoValoracion item) {
		productoValorazionDAO.update(item);
	}

}
