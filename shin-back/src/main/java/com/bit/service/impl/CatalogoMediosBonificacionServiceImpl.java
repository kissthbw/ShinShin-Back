package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoMediosBonificacionDAO;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.service.CatalogoMediosBonificacionService;

@Service
public class CatalogoMediosBonificacionServiceImpl implements CatalogoMediosBonificacionService {

	@Autowired
	private CatalogoMediosBonificacionDAO catalogoMediosBonificacionDAO;

	@Override
	@Transactional
	public List<CatalogoMediosBonificacion> getCatalogoMediosBonificacion() {
		List<CatalogoMediosBonificacion> list = catalogoMediosBonificacionDAO.getCatalogoMediosBonificacion();
		return list;
	}

	@Override
	@Transactional
	public void guardarCatalogoMediosBonificacion(CatalogoMediosBonificacion item) {
		catalogoMediosBonificacionDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarCatalogoMediosBonificacion(CatalogoMediosBonificacion item) {
		catalogoMediosBonificacionDAO.save(item);

	}

}
