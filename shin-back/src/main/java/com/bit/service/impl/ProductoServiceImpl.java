package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ProductoDAO;
import com.bit.model.Producto;
import com.bit.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDAO productoDAO;
	
	
	@Override
	@Transactional
	public List<Producto> getProductos() {
		List<Producto> list = productoDAO.getProductos();
		return list;
	}


	@Override
	@Transactional
	public void guardarProductos(Producto item) {
		productoDAO.save(item);
	}


	@Override
	@Transactional
	public void actualizarProductos(Producto item) {
		productoDAO.update(item);
		
	}

}
