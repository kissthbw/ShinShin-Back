package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ProductoValoracionDAO;
import com.bit.model.ProductoValoracion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProductoValoracionService;

@Service
public class ProductoValoracionServiceImpl implements ProductoValoracionService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoValoracionServiceImpl.class);
	
	@Autowired
	private ProductoValoracionDAO productoValorazionDAO;

	@Override
	@Transactional
	public ListItemsRSP getProductosValoracion() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtebiendo lista de productos valorados");
		
		List<ProductoValoracion> list = productoValorazionDAO.getProductosValoracion();
		rsp.setProductoValoraciones(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse guardarProductosValoracion(ProductoValoracion item) {
		
		log.info("Guardando valoracion de producto");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = productoValorazionDAO.save(item);
		rsp.setId(item.getIdProductoValoracion());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarProductosValoracion(ProductoValoracion item) {
		
		log.info("Actualizando valoracion de producto");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		productoValorazionDAO.findByPK(item.getIdProductoValoracion());
		
		item = productoValorazionDAO.update(item);
		rsp.setId(item.getIdProductoValoracion());
		return rsp;
	}

}
