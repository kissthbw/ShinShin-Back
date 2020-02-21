package com.bit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_tienda_pattern")
public class CatalogoTiendaPattern implements Serializable {

	private static final long serialVersionUID = -5586703825942889977L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "id_tienda")
	private String idTienda;

	@Column(name = "id_pattern")
	private String idPattern;

	@Column(name = "pattern")
	private String pattern;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(String idTienda) {
		this.idTienda = idTienda;
	}

	public String getIdPattern() {
		return idPattern;
	}

	public void setIdPattern(String idPattern) {
		this.idPattern = idPattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String toString() {
		return "CatalogoTiendaPattern [" + (id != null ? "id=" + id + ", " : "")
				+ (idTienda != null ? "idTienda=" + idTienda + ", " : "")
				+ (idPattern != null ? "idPattern=" + idPattern + ", " : "")
				+ (pattern != null ? "pattern=" + pattern : "") + "]";
	}

}
