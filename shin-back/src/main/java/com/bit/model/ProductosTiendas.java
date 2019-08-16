package com.bit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "productos_tiendas")
public class ProductosTiendas {

	@Id
	@Column(name = "id_producto_tienda")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProductoTienda;

	@Column(name = "producto_tienda")
	private String productoTienda;

	@Column(name = "id_producto")
	private Long idProducto;

	@Column(name = "id_catalogo_tienda")
	private Long idCatalogoTienda;

	// Obtener productos por tienda
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "productos_tiendas", joinColumns = { @JoinColumn(name = "id_producto") }, inverseJoinColumns = {
			@JoinColumn(name = "id_catalogo_tienda") })
	private List<ProductosTiendas> productosTiendas = new ArrayList<>();

	public Long getIdProductoTienda() {
		return idProductoTienda;
	}

	public void setIdProductoTienda(Long idProductoTienda) {
		this.idProductoTienda = idProductoTienda;
	}

	public String getProductoTienda() {
		return productoTienda;
	}

	public void setProductoTienda(String productoTienda) {
		this.productoTienda = productoTienda;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdCatalogoTienda() {
		return idCatalogoTienda;
	}

	public void setIdCatalogoTienda(Long idCatalogoTienda) {
		this.idCatalogoTienda = idCatalogoTienda;
	}

	public List<ProductosTiendas> getProductosTiendas() {
		return productosTiendas;
	}

	public void setProductosTiendas(List<ProductosTiendas> productosTiendas) {
		this.productosTiendas = productosTiendas;
	}
}
