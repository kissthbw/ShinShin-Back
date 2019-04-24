package com.bit.service;

import java.util.List;

import com.bit.model.Marca;

public interface MarcaService {

	List<Marca> getMarca();

	void guardarMarcas(Marca item);

	void actualizarMarcas(Marca item);

}
