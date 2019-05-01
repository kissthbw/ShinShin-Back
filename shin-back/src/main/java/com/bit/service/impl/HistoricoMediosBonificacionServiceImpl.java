package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.service.HistoricoMediosBonificacionService;

@Service
public class HistoricoMediosBonificacionServiceImpl implements HistoricoMediosBonificacionService {

	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;

	@Override
	@Transactional
	public List<HistoricoMediosBonificacion> getHistoricosMediosBonificacion() {
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionDAO.getHistoricosMediosBonificacion();
		return list;
	}

	@Override
	@Transactional
	public void guardarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		historicoMediosBonificacionDAO.save(item);
	}

	@Override
	@Transactional
	public void actualizarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		historicoMediosBonificacionDAO.update(item);
	}

}
