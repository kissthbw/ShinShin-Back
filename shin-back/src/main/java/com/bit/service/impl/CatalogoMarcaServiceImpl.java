package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoMarcaDAO;
import com.bit.model.CatalogoMarca;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoMarcaService;

@Service
public class CatalogoMarcaServiceImpl implements CatalogoMarcaService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoMarcaServiceImpl.class);
	
	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;

	@Override
	@Transactional
	public List<CatalogoMarca> getCatalogoMarca() {
		
		log.info("Obteniendo lista de marcas");
		
		List<CatalogoMarca> list = catalogoMarcaDAO.getCatalogoMarca();
		
		return list;
	}

	@Override
	@Transactional
	public SimpleResponse registrarMarcas(CatalogoMarca item) {
		
		log.info("Registrando una marca");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		item = catalogoMarcaDAO.save(item);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarMarcas(CatalogoMarca item) {
		
		log.info("Actializando una marca");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoMarcaDAO.findByPK(item.getIdCatalogoMarca());
		
		item = catalogoMarcaDAO.update(item);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;
	}

}
