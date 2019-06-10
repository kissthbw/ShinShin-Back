package com.bit.service;

import java.util.List;

import com.bit.model.CatalogoTienda;
import com.bit.model.dto.SimpleResponse;

public interface CatalogoTiendaService {
	
	List<CatalogoTienda> getCatalogoTienda();
	
	SimpleResponse registrarTiendas(CatalogoTienda item);
	
	SimpleResponse actualizarTiendas(CatalogoTienda item);
}
