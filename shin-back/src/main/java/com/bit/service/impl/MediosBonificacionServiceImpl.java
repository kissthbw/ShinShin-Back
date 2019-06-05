package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.MediosBonificacionDAO;
import com.bit.model.MediosBonificacion;
import com.bit.service.MediosBonificacionService;

@Service
public class MediosBonificacionServiceImpl implements MediosBonificacionService {
	
	private static final Logger log = LoggerFactory.getLogger(MediosBonificacionService.class);
	
	@Autowired
	private MediosBonificacionDAO mediosBonificacionDAO;

	@Override
	@Transactional
	public List<MediosBonificacion> getMediosBonificacion() {
		
		log.info("Obteniendo lista de medios de bonificacion");
		
		List<MediosBonificacion> list = mediosBonificacionDAO.getMediosBonificacion();
		return list;
	}

	@Override
	@Transactional
	public void guardarMediosBonificacion(MediosBonificacion item) {
		
		log.info("Guardando medio de bonificacion");
		
		mediosBonificacionDAO.save(item);

	}

	@Override
	@Transactional
	public void actualizarMediosBonificacion(MediosBonificacion item) {
		
		log.info("Actualizando medio de bonificacion");
		
		mediosBonificacionDAO.save(item);

	}

}
