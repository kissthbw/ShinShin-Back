package com.bit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_tipo_bancaria")
public class CatalogoTipoBancaria implements Serializable{

	private static final long serialVersionUID = -2002802280058573650L;
	
	@Id
	@Column(name = "id_tipo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipo;
	
	@Column(name = "descripcion_bancaria")
	private String descripcionBancaria;

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcionBancaria() {
		return descripcionBancaria;
	}

	public void setDescripcionBancaria(String descripcionBancaria) {
		this.descripcionBancaria = descripcionBancaria;
	}
	
	

}
