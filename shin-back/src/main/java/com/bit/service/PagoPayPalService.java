package com.bit.service;

import java.util.List;

import com.bit.model.PagoPayPal;

public interface PagoPayPalService {
	
	List<PagoPayPal> getPagosPayPal();

	void guardarPagosPayPal(PagoPayPal item);

	void actualizarPagosPayPal(PagoPayPal item);

}
