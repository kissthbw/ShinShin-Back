package com.bit.model.dto.response;

import com.bit.exception.TicketException;

public class HEBTicketRSP extends OCRTicketRSP {

	private static final long serialVersionUID = -8116861224954611835L;

	private String tra;
	private String folio;

	public String getTra() {
		return tra;
	}

	public void setTra(String tra) {
		this.tra = tra;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void validarTicket() throws TicketException {
		if (getTra() != null && getFolio() != null) {

		} else {
			Throwable t = new Throwable("Identificadores de transaccion o fechas incompletas");
			throw new TicketException("Ticket con identificadores incompletos", t, 500);
		}
	}
}
