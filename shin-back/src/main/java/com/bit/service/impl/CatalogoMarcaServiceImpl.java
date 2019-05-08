package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoMarcaDAO;
import com.bit.model.CatalogoMarca;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoMarcaService;

@Service
public class CatalogoMarcaServiceImpl implements CatalogoMarcaService {

	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;

	@Override
	@Transactional
	public List<CatalogoMarca> getCatalogoMarca() {
		List<CatalogoMarca> list = catalogoMarcaDAO.getCatalogoMarca();
		return list;
	}

	@Override
	@Transactional
	public SimpleResponse registrarMarcas(CatalogoMarca item) {

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
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoMarcaDAO.findByPK(item.getIdCatalogoMarca());
		
		item = catalogoMarcaDAO.update(item);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;

	}

}
