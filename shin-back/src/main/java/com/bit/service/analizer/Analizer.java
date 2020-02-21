package com.bit.service.analizer;

import java.util.List;
import java.util.Map;

import com.bit.exception.TicketException;
import com.bit.model.dto.response.OCRTicketRSP;

public interface Analizer {
	OCRTicketRSP analize(List<String> lineas, boolean fake) throws TicketException;
	Map<String, String> obtieneCatalogoPattern( String idTienda );
}
