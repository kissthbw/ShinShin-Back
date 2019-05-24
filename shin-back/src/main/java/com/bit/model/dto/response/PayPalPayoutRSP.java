package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.List;

import com.bit.model.dto.request.PayPalSenderHeader;

public class PayPalPayoutRSP implements Serializable {

	private static final long serialVersionUID = 6539737363746156730L;

	private PayPalBatchHeader batch_header;
	private List<PayPalLink> links;

	public PayPalBatchHeader getBatch_header() {
		return batch_header;
	}

	public void setBatch_header(PayPalBatchHeader batch_header) {
		this.batch_header = batch_header;
	}

	public List<PayPalLink> getLinks() {
		return links;
	}

	public void setLinks(List<PayPalLink> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "PayPalPayoutRSP [batch_header=" + batch_header + ", links=" + links + "]";
	}

}

class PayPalBatchHeader {
	private String payout_batch_id;
	private String batch_status;
	private PayPalSenderHeader sender_batch_header;

	public String getPayout_batch_id() {
		return payout_batch_id;
	}

	public void setPayout_batch_id(String payout_batch_id) {
		this.payout_batch_id = payout_batch_id;
	}

	public String getBatch_status() {
		return batch_status;
	}

	public void setBatch_status(String batch_status) {
		this.batch_status = batch_status;
	}

	public PayPalSenderHeader getSender_batch_header() {
		return sender_batch_header;
	}

	public void setSender_batch_header(PayPalSenderHeader sender_batch_header) {
		this.sender_batch_header = sender_batch_header;
	}

	@Override
	public String toString() {
		return "PayPalBatchHeader [payout_batch_id=" + payout_batch_id + ", batch_status=" + batch_status
				+ ", sender_batch_header=" + sender_batch_header + "]";
	}

}

class PayPalLink {
	private String href;
	private String rel;
	private String method;
	private String encType;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getEncType() {
		return encType;
	}

	public void setEncType(String encType) {
		this.encType = encType;
	}

	@Override
	public String toString() {
		return "PayPalLink [href=" + href + ", rel=" + rel + ", method=" + method + ", encType=" + encType + "]";
	}

}