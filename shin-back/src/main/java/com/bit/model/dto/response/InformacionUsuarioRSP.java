package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.List;

import com.bit.model.MediosBonificacion;

public class InformacionUsuarioRSP implements Serializable{

	private static final long serialVersionUID = 5264924332432364793L;
	
	private String nombreUsuario;
	private double bonificacion;
	private List<MediosBonificacion> mediosBonificacion;

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

	public List<MediosBonificacion> getMediosBonificacion() {
		return mediosBonificacion;
	}

	public void setMediosBonificacion(List<MediosBonificacion> mediosBonificacion) {
		this.mediosBonificacion = mediosBonificacion;
	}
	
	
}
