package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.MediosBonificacionDAO;
import com.bit.model.MediosBonificacion;
import com.bit.service.MediosBonificacionService;

@Service
public class MediosBonificacionServiceImpl implements MediosBonificacionService {

	@Autowired
	private MediosBonificacionDAO mediosBonificacionDAO;

	@Override
	@Transactional
	public List<MediosBonificacion> getMediosBonificacion() {
		List<MediosBonificacion> list = mediosBonificacionDAO.getMediosBonificacion();
		return list;
	}

	@Override
	@Transactional
	public void guardarMediosBonificacion(MediosBonificacion item) {
		mediosBonificacionDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarMediosBonificacion(MediosBonificacion item) {
		mediosBonificacionDAO.save(item);

	}

}
