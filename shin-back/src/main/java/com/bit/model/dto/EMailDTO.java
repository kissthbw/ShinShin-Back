package com.bit.model.dto;

import java.io.Serializable;

import com.sendgrid.helpers.mail.objects.Personalization;

public class EMailDTO implements Serializable {

	private static final long serialVersionUID = 5762184086962093304L;
	
	private String toAccount;
	private String subject;
	private String body;
	private Personalization personalization = new Personalization();
	private String templateId;

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

	public Personalization getPersonalization() {
		return personalization;
	}

	public void setPersonalization(Personalization personalization) {
		this.personalization = personalization;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
