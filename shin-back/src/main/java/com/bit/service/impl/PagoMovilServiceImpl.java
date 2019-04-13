package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.PagoMovilDAO;
import com.bit.model.PagoMovil;
import com.bit.service.PagoMovilService;

@Service
public class PagoMovilServiceImpl implements PagoMovilService {

	@Autowired
	private PagoMovilDAO pagoMovilDAO;

	@Override
	@Transactional
	public List<PagoMovil> getPagosMoviles() {
		List<PagoMovil> list = pagoMovilDAO.getPagosMoviles();
		return list;
	}

	@Override
	@Transactional
	public void guardarPagosMoviles(PagoMovil item) {
		pagoMovilDAO.save(item);
	}

	@Override
	@Transactional
	public void actualizarPagosMoviles(PagoMovil item) {
		pagoMovilDAO.save(item);
	}

}
