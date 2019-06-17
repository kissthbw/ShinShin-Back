package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.MediosBonificacionDAO;
import com.bit.model.MediosBonificacion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.MediosBonificacionService;

@Service
public class MediosBonificacionServiceImpl implements MediosBonificacionService {
	
	private static final Logger log = LoggerFactory.getLogger(MediosBonificacionServiceImpl.class);
	
	@Autowired
	private MediosBonificacionDAO mediosBonificacionDAO;

	@Override
	@Transactional
	public ListItemsRSP getMediosBonificacion() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo lista de medios de bonificacion");
		
		List<MediosBonificacion> list = mediosBonificacionDAO.getMediosBonificacion();
		
		rsp.setMediosBonificaciones(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse guardarMediosBonificacion(MediosBonificacion item) {
		
		log.info("Guardando medio de bonificacion");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = mediosBonificacionDAO.save(item);
		rsp.setId(item.getIdMediosBonificacion());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarMediosBonificacion(MediosBonificacion item) {
		
		log.info("Actualizando medio de bonificacion");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		mediosBonificacionDAO.findByPK(item.getIdMediosBonificacion());
		
		item = mediosBonificacionDAO.update(item);
		rsp.setId(item.getIdMediosBonificacion());
		return rsp;

	}

}
