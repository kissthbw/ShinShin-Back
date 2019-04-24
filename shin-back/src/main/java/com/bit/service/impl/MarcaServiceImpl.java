package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.MarcaDAO;
import com.bit.model.Marca;
import com.bit.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaDAO marcaDAO;

	@Override
	@Transactional
	public List<Marca> getMarca() {
		List<Marca> list = marcaDAO.getMarca();
		return list;
	}

	@Override
	@Transactional
	public void guardarMarcas(Marca item) {
		marcaDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarMarcas(Marca item) {
		marcaDAO.save(item);

	}

}
