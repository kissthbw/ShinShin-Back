package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "productos_tiendas")
public class ProductosTiendas {

	@Id
	@Column(name = "id_producto_tienda")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProductoTienda;

	@Column(name = "producto_tienda")
	private String productoTienda;

//	@Column(name = "id_producto")
//	private Long idProducto;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;
	
	
//	@Column(name = "id_catalogo_tienda")
//	private Long idCatalogoTienda;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_catalogo_tienda")
    private CatalogoTienda catalogoTienda;

	// Obtener productos por tienda
//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
//	@JoinTable(name = "productos_tiendas", joinColumns = { @JoinColumn(name = "id_producto") }, inverseJoinColumns = {
//			@JoinColumn(name = "id_catalogo_tienda") })
//	private List<ProductosTiendas> productosTiendas = new ArrayList<>();

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

//	public Long getIdProducto() {
//		return idProducto;
//	}
//
//	public void setIdProducto(Long idProducto) {
//		this.idProducto = idProducto;
//	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public CatalogoTienda getCatalogoTienda() {
		return catalogoTienda;
	}

	public void setCatalogoTienda(CatalogoTienda catalogoTienda) {
		this.catalogoTienda = catalogoTienda;
	}

	@Override
	public String toString() {
		return "ProductosTiendas [" + (idProductoTienda != null ? "idProductoTienda=" + idProductoTienda + ", " : "")
				+ (productoTienda != null ? "productoTienda=" + productoTienda + ", " : "")
				+ (producto != null ? "producto=" + producto + ", " : "")
				+ (catalogoTienda != null ? "catalogoTienda=" + catalogoTienda : "") + "]";
	}

	

//	public List<ProductosTiendas> getProductosTiendas() {
//		return productosTiendas;
//	}
//
//	public void setProductosTiendas(List<ProductosTiendas> productosTiendas) {
//		this.productosTiendas = productosTiendas;
//	}
}
