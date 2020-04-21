package com.bit.service;

import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface CatalogoMediosBonificacionService {

	ListItemsRSP getCatalogoMediosBonificacion();
	
	ListItemsRSP getCatalogoTiposBancaria();

	SimpleResponse registrarCatalogoMediosBonificacion(CatalogoMediosBonificacion item);

	SimpleResponse actualizarCatalogoMediosBonificacion(CatalogoMediosBonificacion item);
	
	CatalogoMediosBonificacion findById(Long id);

	SimpleResponse eliminaMedioBonificacion(CatalogoMediosBonificacion item);

}
