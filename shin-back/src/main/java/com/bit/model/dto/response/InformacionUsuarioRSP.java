package com.bit.model.dto.response;

import java.io.Serializable;

public class InformacionUsuarioRSP implements Serializable{

	private static final long serialVersionUID = 5264924332432364793L;
	
	private String nombreUsuario;
	private double bonificacion;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public double getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(double bonificacion) {
		this.bonificacion = bonificacion;
	}
}
