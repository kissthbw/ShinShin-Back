package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pago_movil")
public class PagoMovil {

	@Id
	@Column(name = "tel_movil")
	private String telMovil;

	@Column(name = "compania")
	private String compania;

	@Column(name = "saldo")
	private double saldo;

	public void setTelMovil(String telMovil) {
		this.telMovil = telMovil;
	}

	public String getTelMovil() {
		return telMovil;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getCompania() {
		return compania;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

}
