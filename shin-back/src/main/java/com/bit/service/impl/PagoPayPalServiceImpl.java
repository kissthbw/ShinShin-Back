package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.PagoPayPalDAO;
import com.bit.model.PagoPayPal;
import com.bit.service.PagoPayPalService;

@Service
public class PagoPayPalServiceImpl implements PagoPayPalService {

	@Autowired
	private PagoPayPalDAO pagoPayPalDAO;

	@Override
	@Transactional
	public List<PagoPayPal> getPagosPayPal() {
		List<PagoPayPal> list = pagoPayPalDAO.getPagosPayPal();
		return list;
	}

	@Override
	@Transactional
	public void guardarPagosPayPal(PagoPayPal item) {
		pagoPayPalDAO.save(item);
	}

	@Override
	@Transactional
	public void actualizarPagosPayPal(PagoPayPal item) {
		pagoPayPalDAO.save(item);
	}

}
