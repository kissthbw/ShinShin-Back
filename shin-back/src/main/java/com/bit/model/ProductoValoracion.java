package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "producto_valoracion")
public class ProductoValoracion {

	@Id
	@Column(name = "id_producto_valoracion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProductoValoracion;

	@Column(name = "valoracion")
	private int valoracion;

	@Column(name = "comentario")
	private String comentario;

	// Mapeo contra Producto
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id_producto")
	private Producto producto;

	// Mapeo contra Usuario
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario;

	public void setIdProductoValoracion(Long idProductoValoracion) {
		this.idProductoValoracion = idProductoValoracion;
	}

	public Long getIdProductoValoracion() {
		return idProductoValoracion;
	}

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

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
