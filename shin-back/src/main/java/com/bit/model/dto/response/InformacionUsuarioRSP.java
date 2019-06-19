package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InformacionUsuarioRSP implements Serializable{

	private static final long serialVersionUID = 5264924332432364793L;
	
	private String nombreUsuario;
	private double bonificacion;
	private List<MedioBonificacionUsuario> mediosBonificacion = new ArrayList<>();

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

	public List<MedioBonificacionUsuario> getMediosBonificacion() {
		return mediosBonificacion;
	}

	public void setMediosBonificacion(List<MedioBonificacionUsuario> mediosBonificacion) {
		this.mediosBonificacion = mediosBonificacion;
	}
	
	public void addToList(MedioBonificacionUsuario item) {
		this.mediosBonificacion.add(item);
	}
}
