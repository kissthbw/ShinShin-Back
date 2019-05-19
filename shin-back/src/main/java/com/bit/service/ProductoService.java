package com.bit.service;

import java.util.List;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;

public interface ProductoService {

	/**
	 * Obtiene la lista de productos registrados en BD Pensar en mejorar este
	 * servicio agregando: Paginado. Total de registros a devolver. Filtardo.
	 * Ordenado.
	 * 
	 * @return
	 */
	List<Producto> getProductos();

	SimpleResponse registrarProductos(Producto item);

	SimpleResponse actualizarProductos(Producto item);

	List<Producto> getProductosPorMarca(CatalogoMarca item, Producto i);

	List<Producto> getProductosPorTipo(CatalogoTipoProducto item, Producto i);

	List<Producto> getProductosPorNombre(Producto i);

}
