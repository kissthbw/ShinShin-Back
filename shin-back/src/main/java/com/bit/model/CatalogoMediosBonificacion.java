package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_medios_bonificacion")
public class CatalogoMediosBonificacion {

	@Id
	@Column(name = "id_catalogo_medio_bonificacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCatalogoMedioBonificacion;

	@Column(name = "nombre_medio_bonificacion")
	private String nombreMedioBonificacion;

	@Column(name="active")
	private int active;
	
	public void setactive(int active) {
		this.active=active;
	}
	
	public int getactive() {
		return active;
	}
	public void setIdCatalogoMedioBonificacion(Long idCatalogoMedioBonificacion) {
		this.idCatalogoMedioBonificacion = idCatalogoMedioBonificacion;
	}

	public Long getIdCatalogoMedioBonificacion() {
		return idCatalogoMedioBonificacion;
	}

	public void setNombreMedioBonificacion(String nombreMedioBonificacion) {
		this.nombreMedioBonificacion = nombreMedioBonificacion;
	}

	public String getNombreMedioBonificacion() {
		return nombreMedioBonificacion;
	}

}
