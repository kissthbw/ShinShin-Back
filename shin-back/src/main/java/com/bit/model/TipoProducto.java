package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tipo_producto")
public class TipoProducto {

	@Id
	@Column(name = "id_tipo_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoProducto;

	@Column(name = "nombre_tipo")
	private String nombre_tipo;

	@Column(name = "descripcion")
	private String descripcion;

	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public Long getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setNombre_tipo(String nombre_tipo) {
		this.nombre_tipo = nombre_tipo;
	}

	public String getNombre_tipo() {
		return nombre_tipo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
