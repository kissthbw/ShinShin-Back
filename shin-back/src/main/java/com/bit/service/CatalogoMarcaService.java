package com.bit.service;

import java.util.List;

import com.bit.model.CatalogoMarca;
import com.bit.model.dto.SimpleResponse;

public interface CatalogoMarcaService {

	List<CatalogoMarca> getCatalogoMarca();

	SimpleResponse registrarMarcas(CatalogoMarca item);

	SimpleResponse actualizarMarcas(CatalogoMarca item);

}
