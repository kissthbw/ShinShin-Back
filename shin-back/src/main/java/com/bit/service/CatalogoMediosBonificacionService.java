package com.bit.service;

import java.util.List;

import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.dto.SimpleResponse;

public interface CatalogoMediosBonificacionService {

	List<CatalogoMediosBonificacion> getCatalogoMediosBonificacion();

	SimpleResponse registrarCatalogoMediosBonificacion(CatalogoMediosBonificacion item);

	SimpleResponse actualizarCatalogoMediosBonificacion(CatalogoMediosBonificacion item);

}
