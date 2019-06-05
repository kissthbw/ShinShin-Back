package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoTipoProductoDAO;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoTipoProductoService;

@Service
public class CatalogoTipoProductoServiceImpl implements CatalogoTipoProductoService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoTipoProductoService.class);
	
	@Autowired
	private CatalogoTipoProductoDAO catalogoTipoProductoDAO;

	@Override
	@Transactional
	public List<CatalogoTipoProducto> getCatalogoTipoProductos() {
		
		log.info("Obtiendo una lista de productos por su tipo");
		
		List<CatalogoTipoProducto> list = catalogoTipoProductoDAO.getCatalogoTipoProductos();
		return list;
	}

	@Override
	@Transactional
	public SimpleResponse registrarCatalogoTipoProductos(CatalogoTipoProducto item) {
		
		log.info("Registrando tipo de productos");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = catalogoTipoProductoDAO.save(item);
		rsp.setId(item.getIdCatalogoTipoProducto());
		return rsp;
	}

	@Override
	public SimpleResponse actualizarCatalogoTipoProductos(CatalogoTipoProducto item) {
		
		log.info("Actualizando tipo de productos");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoTipoProductoDAO.findByPK(item.getIdCatalogoTipoProducto());
		
		item = catalogoTipoProductoDAO.update(item);
		rsp.setId(item.getIdCatalogoTipoProducto());
		return rsp;
	}
}
