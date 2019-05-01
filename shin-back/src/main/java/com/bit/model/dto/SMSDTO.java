package com.bit.model.dto;

import java.io.Serializable;

public class SMSDTO implements Serializable {

	private static final long serialVersionUID = 4541599170248860399L;
	
	private String toMobileNumber;
	private String body;

	public String getToMobileNumber() {
		return toMobileNumber;
	}

	public void setToMobileNumber(String toMobileNumber) {
		this.toMobileNumber = toMobileNumber;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
