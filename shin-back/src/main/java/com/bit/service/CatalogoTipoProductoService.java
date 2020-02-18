package com.bit.service;

import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface CatalogoTipoProductoService {

	ListItemsRSP getCatalogoTipoProductos();

	SimpleResponse registrarCatalogoTipoProductos(MultipartFile file, CatalogoTipoProducto item);

	SimpleResponse actualizarCatalogoTipoProductos(MultipartFile file, CatalogoTipoProducto item);
	
	SimpleResponse eliminaCatalogoTipoProductos(CatalogoTipoProducto item);
	
	CatalogoTipoProducto findById(Long id);

}
