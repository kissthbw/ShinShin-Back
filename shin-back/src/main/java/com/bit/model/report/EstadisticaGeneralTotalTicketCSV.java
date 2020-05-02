package com.bit.model.report;

import java.math.BigInteger;
import java.util.Date;

public class EstadisticaGeneralTotalTicketCSV {

	private Date fecha;

	private BigInteger totalEscaneos;
	
	public EstadisticaGeneralTotalTicketCSV() {
		
	}

	public EstadisticaGeneralTotalTicketCSV(Date fecha) {
		super();
		this.fecha = fecha;
		this.totalEscaneos = new BigInteger("0");
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigInteger getTotalEscaneos() {
		return totalEscaneos;
	}

	public void setTotalEscaneos(BigInteger totalEscaneos) {
		this.totalEscaneos = totalEscaneos;
	}
}
