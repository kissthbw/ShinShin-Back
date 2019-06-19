package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bit.model.MediosBonificacion;

public class MedioBonificacionUsuario implements Serializable {

	private static final long serialVersionUID = -1209062103964728387L;

	private String nombreMedioBonificacion;
	private List<MediosBonificacion> list = new ArrayList<>();

	public String getNombreMedioBonificacion() {
		return nombreMedioBonificacion;
	}

	public void setNombreMedioBonificacion(String nombreMedioBonificacion) {
		this.nombreMedioBonificacion = nombreMedioBonificacion;
	}

	public List<MediosBonificacion> getList() {
		return list;
	}

	public void setList(List<MediosBonificacion> list) {
		this.list = list;
	}
	
	public void addToList( MediosBonificacion item ) {
		this.list.add(item);
	}

}
