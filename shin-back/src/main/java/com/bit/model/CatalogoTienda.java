package com.bit.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

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
	
	@Column
	private int active;
	
	@Formula("(select 0)")
	private BigInteger products;
	
	public void setProducts(BigInteger productos) {
		this.products=productos;
	}
	
	public BigInteger getProducts(){
		return this.products;
	}
	
	public int getactive() {
		return this.active;
	}
	
	public void setactive(int active) {
		this.active=active;
	}

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

	@Override
	public String toString() {
		return "CatalogoTienda [" + (idCatalogoTienda != null ? "idCatalogoTienda=" + idCatalogoTienda + ", " : "")
				+ (nombreTienda != null ? "nombreTienda=" + nombreTienda + ", " : "")
				+ (imagenTienda != null ? "imagenTienda=" + imagenTienda : "") + "]";
	}
	
	
}
