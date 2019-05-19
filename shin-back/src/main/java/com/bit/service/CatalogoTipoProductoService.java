package com.bit.service;

import java.util.List;

import com.bit.model.CatalogoTipoProducto;
import com.bit.model.dto.SimpleResponse;

public interface CatalogoTipoProductoService {

	List<CatalogoTipoProducto> getCatalogoTipoProductos();

	SimpleResponse registrarCatalogoTipoProductos(CatalogoTipoProducto item);

	SimpleResponse actualizarCatalogoTipoProductos(CatalogoTipoProducto item);

}
