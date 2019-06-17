package com.bit.service;

import com.bit.model.CatalogoMarca;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface CatalogoMarcaService {

	ListItemsRSP getCatalogoMarca();

	SimpleResponse registrarMarcas(CatalogoMarca item);

	SimpleResponse actualizarMarcas(CatalogoMarca item);

}
