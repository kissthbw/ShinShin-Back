package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.PagoTarjetaDAO;
import com.bit.model.PagoTarjeta;
import com.bit.service.PagoTarjetaService;

@Service
public class PagoTarjetaServiceImpl implements PagoTarjetaService {
	@Autowired
	private PagoTarjetaDAO pagoTarjetaDAO;

	@Override
	@Transactional
	public List<PagoTarjeta> getPagosTarjetas() {
		List<PagoTarjeta> list = pagoTarjetaDAO.getPagosTarjetas();
		return list;
	}

	@Override
	@Transactional
	public void guardarPagosTarjetas(PagoTarjeta item) {
		pagoTarjetaDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarPagosTarjetas(PagoTarjeta item) {
		pagoTarjetaDAO.save(item);

	}

}
