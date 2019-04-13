package com.bit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "pago_tarjeta")
public class PagoTarjeta {

	@Id
	@Column(name = "num_tarjeta")
	private String numTarjeta;

	@Column(name = "banco")
	private String banco;

	@Column(name = "fecha_vencimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	@Column(name = "cvv")
	private String cvv;

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getBanco() {
		return banco;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCvv() {
		return cvv;
	}

}
