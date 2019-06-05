package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.service.HistoricoMediosBonificacionService;

@Service
public class HistoricoMediosBonificacionServiceImpl implements HistoricoMediosBonificacionService {
	
	private static final Logger log = LoggerFactory.getLogger(HistoricoMediosBonificacionService.class);
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;

	@Override
	@Transactional
	public List<HistoricoMediosBonificacion> getHistoricosMediosBonificacion() {
		
		log.info("Obtiene una lista de bonificaciones");
		
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionDAO.getHistoricosMediosBonificacion();
		return list;
	}

	@Override
	@Transactional
	public void guardarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		
		log.info("Guardando historial de medios de bonificacion");
		
		historicoMediosBonificacionDAO.save(item);
	}

	@Override
	@Transactional
	public void actualizarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		
		log.info("Actualizando historial de medios de bonificacion");
		
		historicoMediosBonificacionDAO.update(item);
	}

}
