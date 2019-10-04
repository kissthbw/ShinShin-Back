package com.bit.exception;

public class TicketException extends Exception {

	private static final long serialVersionUID = -7653449595585611131L;

	private int code;

	public TicketException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
