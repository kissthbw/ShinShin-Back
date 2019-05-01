package com.bit.service;

import java.util.List;

import com.bit.model.CatalogoMarca;

public interface CatalogoMarcaService {

	List<CatalogoMarca> getMarca();

	void guardarMarcas(CatalogoMarca item);

	void actualizarMarcas(CatalogoMarca item);

}
