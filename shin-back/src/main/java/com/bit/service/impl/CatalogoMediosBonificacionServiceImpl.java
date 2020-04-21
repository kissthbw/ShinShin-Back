package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.CatalogoMediosBonificacionDAO;
import com.bit.dao.CatalogoTipoBancariaDAO;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTipoBancaria;
import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoMediosBonificacionService;

@Service
public class CatalogoMediosBonificacionServiceImpl implements CatalogoMediosBonificacionService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoMediosBonificacionServiceImpl.class);
	
	@Autowired
	private CatalogoMediosBonificacionDAO catalogoMediosBonificacionDAO;

	@Autowired
	private CatalogoTipoBancariaDAO catalogoTipoBancariaDAO;
	
	@Override
	@Transactional
	public ListItemsRSP getCatalogoMediosBonificacion() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");
		
		log.info("Obteniendo lista de medios de bonificacion");
		
		List<CatalogoMediosBonificacion> list = catalogoMediosBonificacionDAO.getCatalogoMediosBonificacion();
		
		rsp.setMediosBonificacion(list);
		return rsp;
	}

	@Override
	@Transactional
	public ListItemsRSP getCatalogoTiposBancaria() {
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");
		
		log.info("Obteniendo lista de tipos de bancarias");
		
		List<CatalogoTipoBancaria> list = catalogoTipoBancariaDAO.getCatalogoTipoBancaria();
		
		rsp.setTiposBancarias(list);
		return rsp;
	}
	
	@Override
	@Transactional
	public SimpleResponse registrarCatalogoMediosBonificacion(CatalogoMediosBonificacion item) {
		
		log.info("Registrando medios de bonificacion");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		item.setactive(1);
		item = catalogoMediosBonificacionDAO.save(item);
		rsp.setId(item.getIdCatalogoMedioBonificacion());
		
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarCatalogoMediosBonificacion(CatalogoMediosBonificacion item) {
		
		log.info("Actualizando medios de bonificacion");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoMediosBonificacionDAO.findByPK(item.getIdCatalogoMedioBonificacion());
		item.setactive(1);
		item = catalogoMediosBonificacionDAO.update(item);
		rsp.setId(item.getIdCatalogoMedioBonificacion());
		
		return rsp;
	}

	@Override
	@Transactional
	public CatalogoMediosBonificacion findById(Long id) {
		log.info("Buscando producto por id: {}", id);
		CatalogoMediosBonificacion item = catalogoMediosBonificacionDAO.findByPK(id);
		
		return transform(item);
	}
	
	private CatalogoMediosBonificacion transform( CatalogoMediosBonificacion entity ) {
		CatalogoMediosBonificacion item = new CatalogoMediosBonificacion();
		
		item.setIdCatalogoMedioBonificacion( entity.getIdCatalogoMedioBonificacion() );
		item.setNombreMedioBonificacion( entity.getNombreMedioBonificacion() );
		
		return item;
	}
	
	@Override
	@Transactional
	public SimpleResponse eliminaMedioBonificacion(CatalogoMediosBonificacion item) {
		log.info("Eliminado logico de Marca con id: {}", item.getIdCatalogoMedioBonificacion());
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item =  catalogoMediosBonificacionDAO.findByPK(item.getIdCatalogoMedioBonificacion() );
		item.setactive(0);
		catalogoMediosBonificacionDAO.update(item);
		
		item = catalogoMediosBonificacionDAO.update(item);
		/*
		int rows = catalogoMarcaDAO.eliminaMarcaProductos(item);
		log.info("Filas afectadas: "+rows);
		*/
		rsp.setId(item.getIdCatalogoMedioBonificacion());
		return rsp;
	}
	
}
