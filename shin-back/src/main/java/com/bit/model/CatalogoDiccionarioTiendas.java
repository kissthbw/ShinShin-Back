package com.bit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="catalogo_diccionario_tiendas")
public class CatalogoDiccionarioTiendas implements Serializable {

	private static final long serialVersionUID = 2923376227025212269L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="clave_diccionario")
	private String claveDiccionario;
	
	@Column(name="valor_diccionario")
	private String valorDiccionario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClaveDiccionario() {
		return claveDiccionario;
	}

	public void setClaveDiccionario(String claveDiccionario) {
		this.claveDiccionario = claveDiccionario;
	}

	public String getValorDiccionario() {
		return valorDiccionario;
	}

	public void setValorDiccionario(String valorDiccionario) {
		this.valorDiccionario = valorDiccionario;
	}

}
