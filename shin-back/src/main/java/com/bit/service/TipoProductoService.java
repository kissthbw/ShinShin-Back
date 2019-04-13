package com.bit.service;

import java.util.List;

import com.bit.model.TipoProducto;

public interface TipoProductoService {

	List<TipoProducto> getTipoProductos();

	void guardarTipoProductos(TipoProducto item);

	void actualizarTipoProductos(TipoProducto item);

}
