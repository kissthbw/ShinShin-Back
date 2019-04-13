package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "bonificacion")
public class Bonificacion {

	@Id
	@Column(name = "id_bonificacion")
	private int idBonificacion;

	@Column(name = "nombre_bonificacion")
	private String nombreBonificacion;

	@Column(name = "cantidad")
	private double cantidad;

	public void setIdBonificacion(int idBonificacion) {
		this.idBonificacion = idBonificacion;
	}

	public int getIdBonificacion() {
		return idBonificacion;
	}

	public void setNombreBonificacion(String nombreBonificacion) {
		this.nombreBonificacion = nombreBonificacion;
	}

	public String getNombreBonificacion() {
		return nombreBonificacion;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getCantidad() {
		return cantidad;
	}

}
