package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "catalogo_tipo_producto")
public class TipoProducto {

	@Id
	@Column(name = "id_tipo_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoProducto;

	@Column(name = "nombre_tipo_producto")
	private String nombreTipoProducto;

	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public Long getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setNombreTipoProducto(String nombreTipoProducto) {
		this.nombreTipoProducto = nombreTipoProducto;
	}

	public String getNombreTipoProducto() {
		return nombreTipoProducto;
	}

}
