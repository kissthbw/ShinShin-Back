package com.bit.model.dto.response;

import com.bit.exception.TicketException;

public class ChedrauiTicketRSP extends OCRTicketRSP {

	private static final long serialVersionUID = 5180300247160601325L;

	private String suc;
	private String ter;
	private String tra;
	private String folio;

	public String getSuc() {
		return suc;
	}

	public void setSuc(String suc) {
		this.suc = suc;
	}

	public String getTer() {
		return ter;
	}

	public void setTer(String ter) {
		this.ter = ter;
	}

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


	public void validarTicket()throws TicketException {
		if ( getSuc() != null && getTer() != null &&
				getTra() != null && getFolio() != null
				) {

		}
		else {
			Throwable t = new Throwable("Identificadores de transaccion o fechas incompletas");
			throw new TicketException("Ticket con identificadores incompletos", t, 500);
		}
	}
}
