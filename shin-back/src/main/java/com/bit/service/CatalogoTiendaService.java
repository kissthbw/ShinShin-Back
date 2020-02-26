package com.bit.service;

import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoTienda;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface CatalogoTiendaService {
	
	ListItemsRSP getCatalogoTienda();
	
	SimpleResponse registrarTiendas(MultipartFile file, CatalogoTienda item);
	
	SimpleResponse actualizarTiendas(MultipartFile file, CatalogoTienda item);
	
	CatalogoTienda findById(Long id);
	
	SimpleResponse eliminaCatalogoTienda(CatalogoTienda item);
}
