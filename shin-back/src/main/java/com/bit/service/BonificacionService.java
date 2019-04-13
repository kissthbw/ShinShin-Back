package com.bit.service;

import java.util.List;

import com.bit.model.Bonificacion;

public interface BonificacionService {

	List<Bonificacion> getBonificaciones();

	void guardarBonificaciones(Bonificacion item);

	void actualizarBonificaciones(Bonificacion item);

}
