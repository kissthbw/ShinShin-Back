package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_marca")
public class CatalogoMarca {

	@Id
	@Column(name = "id_catalogo_marca")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCatalogoMarca;

	@Column(name = "nombre_marca")
	private String nombreMarca;

	@Column(name = "img_url")
	private String imgUrl;
	
	@Column
	private int active;
	
	public void setactive(int active) {
		this.active=active;
	}
	
	public int getactive() {
		return this.active;
	}

	public void setIdCatalogoMarca(Long idCatalogoMarca) {
		this.idCatalogoMarca = idCatalogoMarca;
	}

	public Long getIdCatalogoMarca() {
		return idCatalogoMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
