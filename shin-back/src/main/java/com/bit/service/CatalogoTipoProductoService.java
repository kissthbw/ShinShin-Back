package com.bit.service;

import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface CatalogoTipoProductoService {

	ListItemsRSP getCatalogoTipoProductos();

	SimpleResponse registrarCatalogoTipoProductos(CatalogoTipoProducto item);

	SimpleResponse actualizarCatalogoTipoProductos(CatalogoTipoProducto item);
	
	CatalogoTipoProducto findById(Long id);

}
