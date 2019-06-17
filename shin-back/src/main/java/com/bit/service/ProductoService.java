package com.bit.service;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface ProductoService {

	/**
	 * Obtiene la lista de productos registrados en BD Pensar en mejorar este
	 * servicio agregando: Paginado. Total de registros a devolver. Filtardo.
	 * Ordenado.
	 * 
	 * @return
	 */
	ListItemsRSP getProductos();

	SimpleResponse registrarProductos(Producto item);

	SimpleResponse actualizarProductos(Producto item);

	ListItemsRSP getProductosPorMarca(CatalogoMarca item, Producto i);

	ListItemsRSP getProductosPorTipo(CatalogoTipoProducto item, Producto i);

	ListItemsRSP getProductosPorNombre(Producto i);

}
