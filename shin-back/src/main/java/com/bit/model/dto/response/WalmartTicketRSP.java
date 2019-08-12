package com.bit.model.dto.response;

public class WalmartTicketRSP extends OCRTicketRSP {

	private static final long serialVersionUID = -1746228918810628829L;

	private String tda;
	private String op;
	private String te;
	private String tr;

	public String getTda() {
		return tda;
	}

	public void setTda(String tda) {
		this.tda = tda;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getTe() {
		return te;
	}

	public void setTe(String te) {
		this.te = te;
	}

	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
	}

}
