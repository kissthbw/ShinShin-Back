package com.bit.model.dto;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

	private static final long serialVersionUID = 3030545634647110162L;

	private String message;
	private Integer code;
	private Long id;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
