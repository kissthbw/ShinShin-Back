package com.bit.service;

import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface CatalogoMarcaService {

	ListItemsRSP getCatalogoMarca();

	SimpleResponse registrarMarcas(MultipartFile file, CatalogoMarca item);

	SimpleResponse actualizarMarcas(MultipartFile file, CatalogoMarca item);
	
	SimpleResponse eliminaMarcas(CatalogoMarca item);
	
	CatalogoMarca findById(Long id);

}
