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
import com.bit.dao.CatalogoMarcaDAO;
import com.bit.model.CatalogoMarca;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.CatalogoMarcaService;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CatalogoMarcaServiceImpl implements CatalogoMarcaService {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoMarcaServiceImpl.class);
	
	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;
	
	@Autowired
	private CloundinaryService cloundinaryService;

	@Override
	@Transactional
	public ListItemsRSP getCatalogoMarca() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo lista de marcas");
		
		List<CatalogoMarca> list = catalogoMarcaDAO.getCatalogoMarca();
		
		rsp.setMarcas(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarMarcas(MultipartFile file, CatalogoMarca item) {
		
		log.info("Registrando una marca");
		
		//Subir imagen a cloudinary
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/marcas/" + item.getNombreMarca(), 
				   "overwrite", true
				);
		
		try {
			log.info("Subiendo imagen de: {}", item.getNombreMarca());
			String url = cloundinaryService.uploadImage(file.getBytes(), params);
			item.setImgUrl( url );
		} catch (IOException e) {
			log.error("Ocurrio un error al subir imagen", e);
		}
		
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		item = catalogoMarcaDAO.save(item);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarMarcas(MultipartFile file, CatalogoMarca item) {
		
		log.info("Actializando una marca");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		catalogoMarcaDAO.findByPK(item.getIdCatalogoMarca());
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/marcas/" + item.getNombreMarca(), 
				   "overwrite", true
				);
		
		try {
			log.info("Subiendo imagen de: {}", item.getNombreMarca());
			String url = cloundinaryService.uploadImage(file.getBytes(), params);
			item.setImgUrl( url );
		} catch (IOException e) {
			log.error("Ocurrio un error al subir imagen", e);
		}
		
		item = catalogoMarcaDAO.update(item);
		rsp.setId(item.getIdCatalogoMarca());
		return rsp;
	}

	@Override
	@Transactional
	public CatalogoMarca findById(Long id) {
		log.info("Buscando marca por id: {}", id);		
		CatalogoMarca item = catalogoMarcaDAO.findByPK(id);
		
		
		return transform(item);
	}
	
	private CatalogoMarca transform( CatalogoMarca entity ) {
		CatalogoMarca item = new CatalogoMarca();
		
		item.setIdCatalogoMarca( entity.getIdCatalogoMarca() );
		item.setNombreMarca( entity.getNombreMarca() );
		item.setImgUrl( entity.getImgUrl() );
		
		return item;
	}
}
