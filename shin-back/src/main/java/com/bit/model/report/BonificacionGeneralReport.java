package com.bit.model.report;

public class BonificacionGeneralReport {

	private String mes;
	private String total;
	
	public BonificacionGeneralReport(String mes, String total) {
		this.mes = mes;
		this.total = total;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
