package com.bit.model.dto;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

	private static final long serialVersionUID = 3030545634647110162L;

	private String message;
	private int code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
