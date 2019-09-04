package com.bit.service;

import com.bit.model.CatalogoTienda;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface CatalogoTiendaService {
	
	ListItemsRSP getCatalogoTienda();
	
	SimpleResponse registrarTiendas(CatalogoTienda item);
	
	SimpleResponse actualizarTiendas(CatalogoTienda item);
	
	CatalogoTienda findById(Long id);
}
