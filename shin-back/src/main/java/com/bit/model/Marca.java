package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "marca")
public class Marca {

	@Id
	@Column(name = "id_marca")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMarca;

	@Column(name = "nombre_marca")
	private String nombreMarca;

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

}
