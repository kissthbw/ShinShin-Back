package com.bit.service;

import com.bit.model.ProductoValoracion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface ProductoValoracionService {

	ListItemsRSP getProductosValoracion();

	SimpleResponse guardarProductosValoracion(ProductoValoracion item);

	SimpleResponse actualizarProductosValoracion(ProductoValoracion item);
}
