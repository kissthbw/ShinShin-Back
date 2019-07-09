package com.bit.service;

import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;

public interface HistoricoMediosBonificacionService {

	ListItemsRSP getHistoricosMediosBonificacion();
	
	ListItemsRSP getHistoricosMediosBonificacionPorUsuario(Usuario item);

	InformacionUsuarioRSP registrarHistoricosMediosBonificacion(HistoricoMediosBonificacion item);

	SimpleResponse actualizarHistoricosMediosBonificacion(HistoricoMediosBonificacion item);
}
