package com.bit.service;

import java.util.List;

import com.bit.model.Producto;

public interface ProductoService {

	/**
	 * Obtiene la lista de productos registrados en BD Pensar en mejorar este
	 * servicio agregando: Paginado. Total de registros a devolver. Filtardo.
	 * Ordenado.
	 * 
	 * @return
	 */
	List<Producto> getProductos();

	void guardarProductos(Producto item);

	void actualizarProductos(Producto item);
}
