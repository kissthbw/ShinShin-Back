package com.bit.service;

import java.util.List;

import com.bit.model.MediosBonificacion;

public interface MediosBonificacionService {

	List<MediosBonificacion> getMediosBonificacion();

	public void guardarMediosBonificacion(MediosBonificacion item);

	public void actualizarMediosBonificacion(MediosBonificacion item);

}
