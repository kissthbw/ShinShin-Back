package com.bit.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.communication.CloundinaryService;
import com.bit.dao.CatalogoTiendaDAO;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoTiendaService;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CatalogoTiendaServiceImpl implements CatalogoTiendaService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoTiendaServiceImpl.class);
	
	@Autowired
	private CloundinaryService cloundinaryService;
	
	@Autowired
	private CatalogoTiendaDAO catalogoTiendaDAO;

	@Override
	@Transactional
	public ListItemsRSP getCatalogoTienda() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");
		
		log.info("Obteniendo una lista de tiendas");
		
		List<CatalogoTienda> list = catalogoTiendaDAO.getCatalogoTienda();
		
		rsp.setTiendas(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarTiendas(MultipartFile file, CatalogoTienda item) {
		
		log.info("Registrando una tienda");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/tiendas/" + item.getNombreTienda(), 
				   "overwrite", true
				);
		
		if ( !file.isEmpty() ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreTienda());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImagenTienda( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		
		item.setactive(1);
		item = catalogoTiendaDAO.save(item);
		rsp.setId(item.getIdCatalogoTienda());
		
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarTiendas(MultipartFile file, CatalogoTienda item) {
		
		log.info("Actualizando valores de una tienda ");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoTiendaDAO.findByPK(item.getIdCatalogoTienda());
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/tiendas/" + item.getNombreTienda(), 
				   "overwrite", true
				);
		
		if ( !file.isEmpty() ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreTienda());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImagenTienda( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		item.setactive(1);
		item = catalogoTiendaDAO.update(item);
		rsp.setId(item.getIdCatalogoTienda());
		
		return rsp;
	}

	@Override
	@Transactional
	public CatalogoTienda findById(Long id) {
		log.info("Buscando tienda por id: {}", id);
		CatalogoTienda item = catalogoTiendaDAO.findByPK(id);
		return transform(item);
	}
	
	private CatalogoTienda transform( CatalogoTienda entity ) {
		CatalogoTienda item = new CatalogoTienda();
		
		item.setIdCatalogoTienda( entity.getIdCatalogoTienda() );
		item.setNombreTienda( entity.getNombreTienda() );
		item.setImagenTienda( entity.getImagenTienda() );
		
		return item;
	}
	
	@Override
	@Transactional
	public SimpleResponse eliminaCatalogoTienda(CatalogoTienda item) {
		log.info("Eliminado logico de tienda con id: {}", item.getIdCatalogoTienda());
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = catalogoTiendaDAO.findByPK( item.getIdCatalogoTienda() );
		item.setactive(0);
		catalogoTiendaDAO.update(item);
		
		item = catalogoTiendaDAO.update(item);
		//int rows = catalogoTiendaDAO.eliminaTienda(item);
		//log.info("Filas afectadas "+rows);;
		rsp.setId(item.getIdCatalogoTienda());
		return rsp;
	}
}
