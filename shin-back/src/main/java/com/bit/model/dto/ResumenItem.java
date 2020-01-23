package com.bit.model.dto;

import java.math.BigInteger;

public class ResumenItem {

	private String topico;
	private Integer indice;
	private BigInteger totalProductos;
	private BigInteger totalEscaneos;
	private Double totalBonificaciones;

	public ResumenItem() {
	}

	public ResumenItem(String topico, Integer indice, BigInteger totalProductos, BigInteger totalEscaneos,
			Double totalBonificaciones) {
		super();
		this.topico = topico;
		this.indice = indice;
		this.totalProductos = totalProductos;
		this.totalEscaneos = totalEscaneos;
		this.totalBonificaciones = totalBonificaciones;
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

	public BigInteger getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(BigInteger totalProductos) {
		this.totalProductos = totalProductos;
	}

	public BigInteger getTotalEscaneos() {
		return totalEscaneos;
	}

	public void setTotalEscaneos(BigInteger totalEscaneos) {
		this.totalEscaneos = totalEscaneos;
	}

	public Double getTotalBonificaciones() {
		return totalBonificaciones;
	}

	public void setTotalBonificaciones(Double totalBonificaciones) {
		this.totalBonificaciones = totalBonificaciones;
	}

	@Override
	public String toString() {
		return "ResumenItem [" + (topico != null ? "topico=" + topico + ", " : "")
				+ (indice != null ? "indice=" + indice + ", " : "")
				+ (totalProductos != null ? "totalProductos=" + totalProductos + ", " : "")
				+ (totalEscaneos != null ? "totalEscaneos=" + totalEscaneos + ", " : "")
				+ (totalBonificaciones != null ? "totalBonificaciones=" + totalBonificaciones : "") + "]";
	}

}
