package com.bit.model.dto.request;

import java.io.Serializable;
import java.util.List;

/*
 * Este objeto esta basado en el API de PayPal para la realizacion de pagos
 * Para mas detalle ver:
 * https://developer.paypal.com/docs/api/payments.payouts-batch/v1/
 * {
  "sender_batch_header": {
    "sender_batch_id": "2014021803",
    "email_subject": "You have a payout!",
    "email_message": "You have received a payout! Thanks for using our service!"
  },
  "items": [
    {
      "recipient_type": "EMAIL",
      "amount": {
        "value": "2.00",
        "currency": "USD"
      },
      "note": "Thanks for your patronage!",
      "sender_item_id": "201403140001",
      "receiver": "kissthbw@gmail.com"
    }
  ]
}
 */
public class PayPalPayoutRQT implements Serializable {

	private static final long serialVersionUID = -2214193112565767446L;

	private PayPalSenderHeader sender_batch_header;
	private List<PayPalPayout> items;

	public PayPalSenderHeader getSender_batch_header() {
		return sender_batch_header;
	}

	public void setSender_batch_header(PayPalSenderHeader sender_batch_header) {
		this.sender_batch_header = sender_batch_header;
	}

	public List<PayPalPayout> getItems() {
		return items;
	}

	public void setItems(List<PayPalPayout> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PayPalPayoutRQT [sender_batch_header=" + sender_batch_header + ", items=" + items + "]";
	}

}
