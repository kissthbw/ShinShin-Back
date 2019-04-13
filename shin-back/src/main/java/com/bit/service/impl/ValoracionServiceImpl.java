package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ValoracionDAO;
import com.bit.model.Valoracion;
import com.bit.service.ValoracionService;

@Service
public class ValoracionServiceImpl implements ValoracionService {

	@Autowired
	private ValoracionDAO valoracionDAO;

	@Override
	@Transactional
	public List<Valoracion> getValoraciones() {
		List<Valoracion> list = valoracionDAO.getValoraciones();
		return list;
	}

	@Override
	@Transactional
	public void guardarValoraciones(Valoracion item) {
		valoracionDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarValoraciones(Valoracion item) {
		valoracionDAO.save(item);

	}

}
