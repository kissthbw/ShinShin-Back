package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoMarcaDAO;
import com.bit.model.CatalogoMarca;
import com.bit.service.CatalogoMarcaService;

@Service
public class CatalogoMarcaServiceImpl implements CatalogoMarcaService {

	@Autowired
	private CatalogoMarcaDAO marcaDAO;

	@Override
	@Transactional
	public List<CatalogoMarca> getMarca() {
		List<CatalogoMarca> list = marcaDAO.getMarca();
		return list;
	}

	@Override
	@Transactional
	public void guardarMarcas(CatalogoMarca item) {
		marcaDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarMarcas(CatalogoMarca item) {
		marcaDAO.save(item);

	}

}
