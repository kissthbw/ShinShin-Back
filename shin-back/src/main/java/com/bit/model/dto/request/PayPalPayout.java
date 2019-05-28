package com.bit.model.dto.request;

import java.io.Serializable;

/*
 * "recipient_type": "EMAIL",
      "amount": {
        "value": "2.00",
        "currency": "USD"
      },
      "note": "Thanks for your patronage!",
      "sender_item_id": "201403140001",
      "receiver": "kissthbw@gmail.com"
 */
public class PayPalPayout implements Serializable {

	private static final long serialVersionUID = -4467686694126221963L;

	private String recipient_type;
	private PayPalPayoutAmount amount;
	private String note;
	private String sender_item_id;
	private String receiver;

	public String getRecipient_type() {
		return recipient_type;
	}

	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}

	public PayPalPayoutAmount getAmount() {
		return amount;
	}

	public void setAmount(PayPalPayoutAmount amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSender_item_id() {
		return sender_item_id;
	}

	public void setSender_item_id(String sender_item_id) {
		this.sender_item_id = sender_item_id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "PayPalPayout [recipient_type=" + recipient_type + ", amount=" + amount + ", note=" + note
				+ ", sender_item_id=" + sender_item_id + ", receiver=" + receiver + "]";
	}

	
}
