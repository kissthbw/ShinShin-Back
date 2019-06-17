package com.bit.service;

import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;

public interface HistoricoMediosBonificacionService {

	ListItemsRSP getHistoricosMediosBonificacion();

	SimpleResponse registrarHistoricosMediosBonificacion(HistoricoMediosBonificacion item);

	SimpleResponse actualizarHistoricosMediosBonificacion(HistoricoMediosBonificacion item);
}
