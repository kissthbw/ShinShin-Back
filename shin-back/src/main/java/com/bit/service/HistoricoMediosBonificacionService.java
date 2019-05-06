package com.bit.service;

import java.util.List;

import com.bit.model.HistoricoMediosBonificacion;

public interface HistoricoMediosBonificacionService {

	List<HistoricoMediosBonificacion> getHistoricosMediosBonificacion();

	void guardarHistoricosMediosBonificacion(HistoricoMediosBonificacion item);

	void actualizarHistoricosMediosBonificacion(HistoricoMediosBonificacion item);
}
