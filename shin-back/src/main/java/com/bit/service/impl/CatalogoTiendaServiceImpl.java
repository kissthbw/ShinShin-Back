package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoTiendaDAO;
import com.bit.model.CatalogoTienda;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoTiendaService;

@Service
public class CatalogoTiendaServiceImpl implements CatalogoTiendaService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoTiendaServiceImpl.class);
	
	@Autowired
	private CatalogoTiendaDAO catalogoTiendaDAO;

	@Override
	@Transactional
	public List<CatalogoTienda> getCatalogoTienda() {
		
		log.info("Obteniendo una lista de tiendas");
		
		List<CatalogoTienda> list = catalogoTiendaDAO.getCatalogoTienda();
		
		return list;
	}

	@Override
	@Transactional
	public SimpleResponse registrarTiendas(CatalogoTienda item) {
		
		log.info("Registrando una tienda");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = catalogoTiendaDAO.save(item);
		rsp.setId(item.getIdCatalogoTienda());
		
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarTiendas(CatalogoTienda item) {
		
		log.info("Actualizando valores de una tienda ");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoTiendaDAO.findByPK(item.getIdCatalogoTienda());
		
		item = catalogoTiendaDAO.update(item);
		rsp.setId(item.getIdCatalogoTienda());
		
		return rsp;
	}

}
