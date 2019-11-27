package com.bit.model.report;

public class ProductoReport {
	private Long idProducto;
	private String codigoBarras;
	private String nombreProducto;
	private String marca;
	private Boolean banner;

	public ProductoReport(Long idProducto, String codigoBarras, String nombreProducto, String marca, Boolean banner) {
		this.idProducto = idProducto;
		this.codigoBarras = codigoBarras;
		this.nombreProducto = nombreProducto;
		this.marca = marca;
		this.banner = banner;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Boolean getBanner() {
		return banner;
	}

	public void setBanner(Boolean banner) {
		this.banner = banner;
	}

}
