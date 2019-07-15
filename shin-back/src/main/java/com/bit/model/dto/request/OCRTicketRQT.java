package com.bit.model.dto.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OCRTicketRQT implements Serializable {

	private static final long serialVersionUID = -5901826082992777127L;

	private List<String> lineas = new ArrayList<>();

	public List<String> getLineas() {
		return lineas;
	}

	public void setLineas(List<String> lineas) {
		this.lineas = lineas;
	}

}
