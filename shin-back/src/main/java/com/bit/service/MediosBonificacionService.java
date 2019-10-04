package com.bit.service;

import com.bit.model.MediosBonificacion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface MediosBonificacionService {

	ListItemsRSP getMediosBonificacion();

	SimpleResponse guardarMediosBonificacion(MediosBonificacion item);

	SimpleResponse actualizarMediosBonificacion(MediosBonificacion item);
	
	SimpleResponse eliminarMediosBonificacion(MediosBonificacion item);

}
