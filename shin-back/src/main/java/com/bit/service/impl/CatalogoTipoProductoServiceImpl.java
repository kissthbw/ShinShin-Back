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
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoTipoProductoService;

@Service
public class CatalogoTipoProductoServiceImpl implements CatalogoTipoProductoService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoTipoProductoServiceImpl.class);
	
	@Autowired
	private CatalogoTipoProductoDAO catalogoTipoProductoDAO;

	@Override
	@Transactional
	public ListItemsRSP getCatalogoTipoProductos() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtiendo una lista de productos por su tipo");
		
		List<CatalogoTipoProducto> list = catalogoTipoProductoDAO.getCatalogoTipoProductos();
		
		rsp.setTipoProductos(list);
		return rsp;
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
	@Transactional
	public SimpleResponse actualizarCatalogoTipoProductos(CatalogoTipoProducto item) {
		
		log.info("Actualizando tipo de productos");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
//		catalogoTipoProductoDAO.findByPK(item.getIdCatalogoTipoProducto());
		
		item = catalogoTipoProductoDAO.update(item);
		rsp.setId(item.getIdCatalogoTipoProducto());
		return rsp;
	}

	@Override
	@Transactional
	public CatalogoTipoProducto findById(Long id) {
		
		log.info("Buscando departamento por id: {}", id);		
		CatalogoTipoProducto item = catalogoTipoProductoDAO.findByPK(id);
		
		
		return transform(item);
	}
	
	private CatalogoTipoProducto transform( CatalogoTipoProducto entity ) {
		CatalogoTipoProducto item = new CatalogoTipoProducto();
		
		item.setIdCatalogoTipoProducto( entity.getIdCatalogoTipoProducto() );
		item.setNombreTipoProducto( entity.getNombreTipoProducto() );
		
		return item;
	}
}
