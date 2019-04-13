package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.BonificacionDAO;
import com.bit.model.Bonificacion;
import com.bit.service.BonificacionService;

@Service
public class BonificacionServiceImpl implements BonificacionService {

	@Autowired
	private BonificacionDAO bonificacionDAO;

	@Override
	@Transactional
	public List<Bonificacion> getBonificaciones() {
		List<Bonificacion> list = bonificacionDAO.getBonificaciones();
		return list;
	}

	@Override
	public void guardarBonificaciones(Bonificacion item) {
		bonificacionDAO.save(item);
	}

	@Override
	public void actualizarBonificaciones(Bonificacion item) {
		bonificacionDAO.save(item);
	}

}
