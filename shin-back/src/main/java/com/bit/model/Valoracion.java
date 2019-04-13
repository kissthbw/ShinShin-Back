package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "producto_valoracion")
public class Valoracion {

	@Column(name = "valoracion")
	private int valoracion;

	@Column(name = "comentario")
	private String comentario;

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentario() {
		return comentario;
	}

}
