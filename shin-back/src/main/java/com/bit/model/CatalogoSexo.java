package com.bit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_sexo")
public class CatalogoSexo implements Serializable {

	private static final long serialVersionUID = -5586703825942889977L;

	@Id
	@Column(name = "id_catalogo_sexo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCatalogoSexo;

	@Column(name = "nombre_sexo")
	private String nombreSexo;

	public Integer getIdCatalogoSexo() {
		return idCatalogoSexo;
	}

	public void setIdCatalogoSexo(Integer idCatalogoSexo) {
		this.idCatalogoSexo = idCatalogoSexo;
	}

	public String getNombreSexo() {
		return nombreSexo;
	}

	public void setNombreSexo(String nombreSexo) {
		this.nombreSexo = nombreSexo;
	}

}
