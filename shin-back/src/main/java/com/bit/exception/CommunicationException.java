package com.bit.exception;

public class CommunicationException extends Exception {

	private static final long serialVersionUID = -7653449595585611131L;

	private int code;

	public CommunicationException(String message, Throwable cause, int code) {
		super(message, cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
