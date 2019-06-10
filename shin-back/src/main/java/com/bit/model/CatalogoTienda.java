package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_tienda")
public class CatalogoTienda {

	@Id
	@Column(name = "id_catalogo_tienda")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCatalogoTienda;

	@Column(name = "nombre_tienda")
	private String nombreTienda;

	@Column(name = "imagen_tienda")
	private String imagenTienda;

	public Long getIdCatalogoTienda() {
		return idCatalogoTienda;
	}

	public void setIdCatalogoTienda(Long idCatalogoTienda) {
		this.idCatalogoTienda = idCatalogoTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public String getImagenTienda() {
		return imagenTienda;
	}

	public void setImagenTienda(String imagenTienda) {
		this.imagenTienda = imagenTienda;
	}
}
