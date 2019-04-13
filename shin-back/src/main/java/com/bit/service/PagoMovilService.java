package com.bit.service;

import java.util.List;

import com.bit.model.PagoMovil;

public interface PagoMovilService {

	List<PagoMovil> getPagosMoviles();

	void guardarPagosMoviles(PagoMovil item);

	void actualizarPagosMoviles(PagoMovil item);

}
