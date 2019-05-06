package com.bit.service;

import java.util.List;

import com.bit.model.ProductoValoracion;

public interface ProductoValoracionService {

	List<ProductoValoracion> getProductosValoracion();

	void guardarProductosValoracion(ProductoValoracion item);

	void actualizarProductosValoracion(ProductoValoracion item);
}
