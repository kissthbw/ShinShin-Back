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
	private Long idCatalogoMediosBonificacion;

	@Column(name = "nombre_medio_bonificacion")
	private String nombreMedioBonificacion;

	public void setIdCatalogoMediosBonificacion(Long idCatalogoMediosBonificacion) {
		this.idCatalogoMediosBonificacion = idCatalogoMediosBonificacion;
	}

	public Long getIdCatalogoMediosBonificacion() {
		return idCatalogoMediosBonificacion;
	}

	public void setNombreMedioBonificacion(String nombreMedioBonificacion) {
		this.nombreMedioBonificacion = nombreMedioBonificacion;
	}

	public String getNombreMedioBonificacion() {
		return nombreMedioBonificacion;
	}

}
