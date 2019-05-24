package com.bit.model.dto.request;

import java.io.Serializable;

public class PayPalSenderHeader implements Serializable {

	private static final long serialVersionUID = 3200314908208719287L;

	private String sender_batch_id;
	private String email_subject;
	private String email_message;

	public String getSender_batch_id() {
		return sender_batch_id;
	}

	public void setSender_batch_id(String sender_batch_id) {
		this.sender_batch_id = sender_batch_id;
	}

	public String getEmail_subject() {
		return email_subject;
	}

	public void setEmail_subject(String email_subject) {
		this.email_subject = email_subject;
	}

	public String getEmail_message() {
		return email_message;
	}

	public void setEmail_message(String email_message) {
		this.email_message = email_message;
	}

	@Override
	public String toString() {
		return "PayPalSenderHeader [sender_batch_id=" + sender_batch_id + ", email_subject=" + email_subject
				+ ", email_message=" + email_message + "]";
	}
	
	

}
