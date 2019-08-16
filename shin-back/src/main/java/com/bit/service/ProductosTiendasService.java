package com.bit.service;

import com.bit.model.ProductosTiendas;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface ProductosTiendasService {

	ListItemsRSP getProductosTiendas();

	SimpleResponse registrarProductosPorTienda(ProductosTiendas item);

	SimpleResponse actualizarProductosPorTienda(ProductosTiendas item);
}
