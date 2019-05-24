package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoTipoProductoDAO;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoTipoProductoService;

@Service
public class CatalogoTipoProductoServiceImpl implements CatalogoTipoProductoService {

	@Autowired
	private CatalogoTipoProductoDAO catalogoTipoProductoDAO;

	@Override
	@Transactional
	public List<CatalogoTipoProducto> getCatalogoTipoProductos() {
		List<CatalogoTipoProducto> list = catalogoTipoProductoDAO.getCatalogoTipoProductos();
		return list;
	}

	@Override
	@Transactional
	public SimpleResponse registrarCatalogoTipoProductos(CatalogoTipoProducto item) {
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = catalogoTipoProductoDAO.save(item);
		rsp.setId(item.getIdCatalogoTipoProducto());
		return rsp;
	}

	@Override
	public SimpleResponse actualizarCatalogoTipoProductos(CatalogoTipoProducto item) {
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoTipoProductoDAO.findByPK(item.getIdCatalogoTipoProducto());
		
		item = catalogoTipoProductoDAO.update(item);
		rsp.setId(item.getIdCatalogoTipoProducto());
		return rsp;
	}
}
