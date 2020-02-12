package com.bit.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bit.communication.CloundinaryService;
import com.bit.dao.CatalogoTipoProductoDAO;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoTipoProductoService;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CatalogoTipoProductoServiceImpl implements CatalogoTipoProductoService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoTipoProductoServiceImpl.class);
	
	@Autowired
	private CatalogoTipoProductoDAO catalogoTipoProductoDAO;
	
	@Autowired
	private CloundinaryService cloundinaryService;

	@Override
	@Transactional
	public ListItemsRSP getCatalogoTipoProductos() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtiendo una lista de productos por su tipo");
		
		List<CatalogoTipoProducto> list = catalogoTipoProductoDAO.getCatalogoTipoProductos();
		
		rsp.setTipoProductos(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarCatalogoTipoProductos(MultipartFile file, CatalogoTipoProducto item) {
		
		log.info("Registrando tipo de productos");
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/departamentos/" + item.getNombreTipoProducto(), 
				   "overwrite", true
				);
		
		if( file.getSize() != 0 ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreTipoProducto());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImgUrl( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = catalogoTipoProductoDAO.save(item);
		rsp.setId(item.getIdCatalogoTipoProducto());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarCatalogoTipoProductos(MultipartFile file, CatalogoTipoProducto item) {
		
		log.info("Actualizando tipo de productos");
		
//		item = catalogoTipoProductoDAO.update(item);
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/departamentos/" + item.getNombreTipoProducto(), 
				   "overwrite", true
				);
		
		if( file.getSize() != 0 ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreTipoProducto());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImgUrl( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
//		catalogoTipoProductoDAO.findByPK(item.getIdCatalogoTipoProducto());
		
		item = catalogoTipoProductoDAO.update(item);
		rsp.setId(item.getIdCatalogoTipoProducto());
		return rsp;
	}

	@Override
	@Transactional
	public CatalogoTipoProducto findById(Long id) {
		
		log.info("Buscando departamento por id: {}", id);		
		CatalogoTipoProducto item = catalogoTipoProductoDAO.findByPK(id);
		
		
		return transform(item);
	}
	
	private CatalogoTipoProducto transform( CatalogoTipoProducto entity ) {
		CatalogoTipoProducto item = new CatalogoTipoProducto();
		
		item.setIdCatalogoTipoProducto( entity.getIdCatalogoTipoProducto() );
		item.setNombreTipoProducto( entity.getNombreTipoProducto() );
		item.setImgUrl( entity.getImgUrl() );
		
		return item;
	}
}
