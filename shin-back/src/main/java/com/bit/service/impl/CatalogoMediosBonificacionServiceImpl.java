package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoMediosBonificacionDAO;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.service.CatalogoMediosBonificacionService;

@Service
public class CatalogoMediosBonificacionServiceImpl implements CatalogoMediosBonificacionService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoMediosBonificacionService.class);
	
	@Autowired
	private CatalogoMediosBonificacionDAO catalogoMediosBonificacionDAO;

	@Override
	@Transactional
	public List<CatalogoMediosBonificacion> getCatalogoMediosBonificacion() {
		
		log.info("Obteniendo lista de medios de bonificacion");
		
		List<CatalogoMediosBonificacion> list = catalogoMediosBonificacionDAO.getCatalogoMediosBonificacion();
		
		return list;
	}

	@Override
	@Transactional
	public void guardarCatalogoMediosBonificacion(CatalogoMediosBonificacion item) {
		
		log.info("Guardando medios de bonificacion");
		
		catalogoMediosBonificacionDAO.save(item);
	}

	@Override
	@Transactional
	public void actualizarCatalogoMediosBonificacion(CatalogoMediosBonificacion item) {
		
		log.info("Actualizando medios de bonificacion");
		
		catalogoMediosBonificacionDAO.save(item);
	}
}
