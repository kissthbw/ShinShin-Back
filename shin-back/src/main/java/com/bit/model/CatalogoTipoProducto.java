package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_tipo_producto")
public class CatalogoTipoProducto {

	@Id
	@Column(name = "id_catalogo_tipo_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCatalogoTipoProducto;

	@Column(name = "nombre_tipo_producto")
	private String nombreTipoProducto;

	public void setIdCatalogoTipoProducto(Long idCatalogoTipoProducto) {
		this.idCatalogoTipoProducto = idCatalogoTipoProducto;
	}

	public Long getIdCatalogoTipoProducto() {
		return idCatalogoTipoProducto;
	}

	public void setNombreTipoProducto(String nombreTipoProducto) {
		this.nombreTipoProducto = nombreTipoProducto;
	}

	public String getNombreTipoProducto() {
		return nombreTipoProducto;
	}

}
