package com.bit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "producto")
public class Producto {

	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;

	@Column(name = "nombre_prod")
	private String nombreProd;

	@Column(name = "marca")
	private String marca;

	@Column(name = "precio")
	private double precio;

	@Column(name = "codigo_barra")
	private String codigoBarra;

	@Column(name = "tamanio")
	private String tamanio;

	@Column(name = "color_sabor")
	private String colorSabor;

	@Column(name = "contenido")
	private String contenido;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "promo")
	private boolean promo;

	@Column(name = "promo_fecha")
	@Temporal(TemporalType.DATE)
	private Date promo_fecha;

	@Column(name = "imagen")
	private byte imagen;

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}

	public String getNombreProd() {
		return nombreProd;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setColorSabor(String colorSabor) {
		this.colorSabor = colorSabor;
	}

	public String getColorSabor() {
		return colorSabor;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	public boolean isPromo() {
		return promo;
	}

	public void setPromo_fecha(Date promo_fecha) {
		this.promo_fecha = promo_fecha;
	}

	public Date getPromo_fecha() {
		return promo_fecha;
	}

	public void setImagen(byte imagen) {
		this.imagen = imagen;
	}

	public byte getImagen() {
		return imagen;
	}

}
