package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pagopaypal")
public class PagoPayPal {

	@Id
	@Column(name = "codigo_cuenta")
	private String codigoCuenta;

	@Column(name = "saldo")
	private double saldo;

	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public String getCodigoCuenta() {
		return codigoCuenta;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

}
