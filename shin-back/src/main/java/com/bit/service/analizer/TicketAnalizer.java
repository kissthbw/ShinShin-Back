package com.bit.service.analizer;

import java.util.List;

import com.bit.exception.TicketException;
import com.bit.model.dto.response.OCRTicketRSP;

public interface TicketAnalizer {
	OCRTicketRSP analize(List<String> lineas) throws TicketException;
}
