package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "medios_bonificacion")
public class MediosBonificacion {

	@Id
	@Column(name = "id_medios_bonificacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMediosBonificacion;

	@Column(name = "cuenta_medio_bonificacion")
	private String cuentaMediosBonificacion;

	@Column(name = "compania_medio_bonificacion")
	private String companiaMedioBonificacion;

	public void setIdMediosBonificacion(Long idMediosBonificacion) {
		this.idMediosBonificacion = idMediosBonificacion;
	}

	public Long getIdMediosBonificacion() {
		return idMediosBonificacion;
	}

	public void setCuentaMediosBonificacion(String cuentaMediosBonificacion) {
		this.cuentaMediosBonificacion = cuentaMediosBonificacion;
	}

	public String getCuentaMediosBonificacion() {
		return cuentaMediosBonificacion;
	}

	public void setCompaniaMedioBonificacion(String companiaMedioBonificacion) {
		this.companiaMedioBonificacion = companiaMedioBonificacion;
	}

	public String getCompaniaMedioBonificacion() {
		return companiaMedioBonificacion;
	}

}
