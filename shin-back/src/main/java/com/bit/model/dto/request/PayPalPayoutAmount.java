package com.bit.model.dto.request;

import java.io.Serializable;

/*
 * "amount": {
        "value": "2.00",
        "currency": "USD"
      }
 */
public class PayPalPayoutAmount implements Serializable {

	private static final long serialVersionUID = -8718309106776738535L;

	private String value;
	private String currency;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "PayPalPayoutAmount [value=" + value + ", currency=" + currency + "]";
	}
	
	
}
