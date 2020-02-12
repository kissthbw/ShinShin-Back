package com.bit.model.dto;

import java.math.BigInteger;

public class Item {

	private String titulo;
	private String topico;
	private Integer indice;
	private BigInteger total;
	private Double importe;

	public Item() {
	}

	public Item(String topico, BigInteger total) {
		this.topico = topico;
		this.total = total;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public BigInteger getTotal() {
		return total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Item [" + (topico != null ? "topico=" + topico + ", " : "")
				+ (indice != null ? "indice=" + indice + ", " : "") + (total != null ? "total=" + total + ", " : "")
				+ (importe != null ? "importe=" + importe : "") + "]";
	}

	

}
