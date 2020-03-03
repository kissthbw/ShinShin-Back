package com.bit.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

@Entity(name = "catalogo_tipo_producto")
public class CatalogoTipoProducto {

	@Id
	@Column(name = "id_catalogo_tipo_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCatalogoTipoProducto;

	@Column(name = "nombre_tipo_producto")
	private String nombreTipoProducto;

	@Column(name = "img_url")
	private String imgUrl;
	
	@Column(name = "active")
	private boolean active;
	
	@Formula("(select 0)")
	private BigInteger products;
	
	public void setIdCatalogoTipoProducto(Long idCatalogoTipoProducto) {
		this.idCatalogoTipoProducto = idCatalogoTipoProducto;
	}

	public void setProducts(BigInteger productos) {
		this.products=productos;
	}
	
	public BigInteger getProducts(){
		return this.products;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setEstatus(boolean active) {
		this.active = active;
	}

}
