package com.bit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "producto")
public class Producto {

	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;

	@Column(name = "nombre_producto")
	private String nombreProducto;

	@Column(name = "precio")
	private double precio;

	@Column(name = "codigo_barras")
	private String codigoBarras;

	@Column(name = "presentacion")
	private String presentacion;

	@Column(name = "contenido")
	private String contenido;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "aplica_promocion")
	private boolean aplicaPromocion;

	@Column(name = "vigencia_promocion")
	@Temporal(TemporalType.DATE)
	private Date vigenciaPromocion;

	@Column(name = "url_imagen_producto")
	private String urlImagenProducto;

	@Column(name = "cantidad_bonificacion")
	private double cantidadBonificacion;

	@Column(name = "banner")
	private boolean banner;

	@Column(name = "color_banner")
	private String colorBanner;

	// Mapeo contra CatalogoMarca
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_catalogo_marca")
	private CatalogoMarca catalogoMarca;

	// Mapeo contra TipoProducto
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_catalogo_tipo_producto")
	private CatalogoTipoProducto catalogoTipoProducto;

	// Mapeocontra CatalogoTienda
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_catalogo_tienda")
	private CatalogoTienda catalogoTienda;
	
	
//	@ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "productos_tiendas", 
//        joinColumns = { @JoinColumn(name = "id_producto") }, 
//        inverseJoinColumns = { @JoinColumn(name = "id_catalogo_tienda") }
//    )
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_producto")
    List<ProductosTiendas> tiendas = new ArrayList<>();
	
	@Column(name = "img_url")
	private String imgUrl;

	@Column
	private int active;
	
	public int getactive() {
		return this.active;
	}
	
	public void setactive(int active) {
		this.active=active;
	}

	
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
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

	public void setAplicaPromocion(boolean aplicaPromocion) {
		this.aplicaPromocion = aplicaPromocion;
	}

	public boolean isAplicaPromocion() {
		return aplicaPromocion;
	}

	public void setVigenciaPromocion(Date vigenciaPromocion) {
		this.vigenciaPromocion = vigenciaPromocion;
	}

	public Date getVigenciaPromocion() {
		return vigenciaPromocion;
	}

	public void setUrlImagenProducto(String urlImagenProducto) {
		this.urlImagenProducto = urlImagenProducto;
	}

	public String getUrlImagenProducto() {
		return urlImagenProducto;
	}

	public void setCantidadBonificacion(double cantidadBonificacion) {
		this.cantidadBonificacion = cantidadBonificacion;
	}

	public double getCantidadBonificacion() {
		return cantidadBonificacion;
	}

	public void setBanner(boolean banner) {
		this.banner = banner;
	}

	public boolean isBanner() {
		return banner;
	}

	public void setColorBanner(String colorBanner) {
		this.colorBanner = colorBanner;
	}

	public String getColorBanner() {
		return colorBanner;
	}

	public void setCatalogoMarca(CatalogoMarca catalogoMarca) {
		this.catalogoMarca = catalogoMarca;
	}

	public CatalogoMarca getCatalogoMarca() {
		return catalogoMarca;
	}

	public void setCatalogoTipoProducto(CatalogoTipoProducto catalogoTipoProducto) {
		this.catalogoTipoProducto = catalogoTipoProducto;
	}

	public CatalogoTipoProducto getCatalogoTipoProducto() {
		return catalogoTipoProducto;
	}

	public CatalogoTienda getCatalogoTienda() {
		return catalogoTienda;
	}

	public void setCatalogoTienda(CatalogoTienda catalogoTienda) {
		this.catalogoTienda = catalogoTienda;
	}

	public List<ProductosTiendas> getTiendas() {
		return tiendas;
	}

	public void setTiendas(List<ProductosTiendas> tiendas) {
		this.tiendas = tiendas;
	}
	
	public void addTienda( ProductosTiendas tienda ) {
		this.tiendas.add(tienda);
	}
	
	public void removeTienda( CatalogoTienda tienda ) {
		this.tiendas.remove(tienda);
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
