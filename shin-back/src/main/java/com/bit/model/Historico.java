package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "historico")
public class Historico {

	@Column(name = "total_compras")
	private int totalCompras;

	@Column(name = "total_pagado")
	private double totalPagado;

	public void setTotalCompras(int totalCompras) {
		this.totalCompras = totalCompras;
	}

	public int getTotalCompras() {
		return totalCompras;
	}

	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}

	public double getTotalPagado() {
		return totalPagado;
	}

}
