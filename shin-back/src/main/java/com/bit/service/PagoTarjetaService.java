package com.bit.service;

import java.util.List;

import com.bit.model.PagoTarjeta;

public interface PagoTarjetaService {

	List<PagoTarjeta> getPagosTarjetas();

	void guardarPagosTarjetas(PagoTarjeta item);

	void actualizarPagosTarjetas(PagoTarjeta item);

}
