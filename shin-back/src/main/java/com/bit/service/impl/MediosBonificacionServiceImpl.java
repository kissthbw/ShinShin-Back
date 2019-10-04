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
	
	public enum Estatus{
		
		ACTIVO(1),
		BAJA(2);
		
		public final Integer estatus;
		
		private Estatus(Integer estatus) {
			this.estatus = estatus;
		}
	}

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
		
		item.setEstatus( Estatus.ACTIVO.estatus );
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
		
		MediosBonificacion entity = mediosBonificacionDAO.findByPK(item.getIdMediosBonificacion());
		
		//Para el caso de PAYPAL solo se actualiza 
		//id
		//correo
		entity = updateMedioBonificacion(item, entity);
		item = mediosBonificacionDAO.update(entity);
		rsp.setId(item.getIdMediosBonificacion());
		return rsp;

	}
	
	private MediosBonificacion updateMedioBonificacion( MediosBonificacion item, MediosBonificacion entity ) {
		
		
		if ( item.getCatalogoMediosBonificacion().getIdCatalogoMedioBonificacion() == 1 ) {
			entity.setAliasMedioBonificacion( item.getAliasMedioBonificacion() );
			entity.setCuentaMedioBonificacion( item.getCuentaMedioBonificacion() );
			entity.setVigenciaMedioBonificacion( item.getVigenciaMedioBonificacion() );
			entity.setBanco( item.getBanco() );
			entity.setIdTipo( item.getIdTipo() );
		}
		if ( item.getCatalogoMediosBonificacion().getIdCatalogoMedioBonificacion() == 2 ) {
			entity.setAliasMedioBonificacion( item.getAliasMedioBonificacion() );
			entity.setIdCuentaMedioBonificacion( item.getIdCuentaMedioBonificacion() );
			entity.setCuentaMedioBonificacion( item.getCuentaMedioBonificacion() );
		}
		if ( item.getCatalogoMediosBonificacion().getIdCatalogoMedioBonificacion() == 3 ) {
			entity.setAliasMedioBonificacion( item.getAliasMedioBonificacion() );
			entity.setCuentaMedioBonificacion( item.getCuentaMedioBonificacion() );
			entity.setCompaniaMedioBonificacion( item.getCompaniaMedioBonificacion() );
		}
		
		return entity;
	}

	@Override
	@Transactional
	public SimpleResponse eliminarMediosBonificacion(MediosBonificacion item) {
		
		log.info("Eliminando medio de bonificacion con id: {}", item.getIdMediosBonificacion());
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = mediosBonificacionDAO.findByPK(item.getIdMediosBonificacion());
		item.setEstatus( Estatus.BAJA.estatus );
		item = mediosBonificacionDAO.update(item);
		return rsp;
	}

}
