package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.HistoricoMediosBonificacionService;

@Service
public class HistoricoMediosBonificacionServiceImpl implements HistoricoMediosBonificacionService {
	
	private static final Logger log = LoggerFactory.getLogger(HistoricoMediosBonificacionServiceImpl.class);
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;

	@Override
	@Transactional
	public ListItemsRSP getHistoricosMediosBonificacion() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtiene una lista de bonificaciones");
		
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionDAO.getHistoricosMediosBonificacion();
		
		rsp.setHistoricoMediosBonificaciones(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		
		log.info("Registrando historial de medios de bonificacion");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = historicoMediosBonificacionDAO.save(item);
		rsp.setId(item.getIdHistoricoMediosBonificacion());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		
		log.info("Actualizando historial de medios de bonificacion");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		historicoMediosBonificacionDAO.findByPK(item.getIdHistoricoMediosBonificacion());
		
		item = historicoMediosBonificacionDAO.update(item);
		rsp.setId(item.getIdHistoricoMediosBonificacion());
		return rsp;
	}

}
