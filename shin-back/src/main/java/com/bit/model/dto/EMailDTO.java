package com.bit.model.dto;

import java.io.Serializable;

public class EMailDTO implements Serializable {

	private static final long serialVersionUID = 5762184086962093304L;
	
	private String toAccount;
	private String subject;
	private String body;

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
