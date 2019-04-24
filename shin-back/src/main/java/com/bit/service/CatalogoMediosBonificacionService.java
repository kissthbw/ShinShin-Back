package com.bit.service;

import java.util.List;

import com.bit.model.CatalogoMediosBonificacion;

public interface CatalogoMediosBonificacionService {

	List<CatalogoMediosBonificacion> getCatalogoMediosBonificacion();

	void guardarCatalogoMediosBonificacion(CatalogoMediosBonificacion item);

	void actualizarCatalogoMediosBonificacion(CatalogoMediosBonificacion item);

}
