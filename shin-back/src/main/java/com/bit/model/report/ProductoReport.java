package com.bit.model.report;

public class ProductoReport {
	private Long idProducto;
	private String codigoBarras;
	private String nombreProducto;
	private String marca;
	private int banner;

	public ProductoReport(Long idProducto, String codigoBarras, String nombreProducto, String marca, int banner) {
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

	public int getBanner() {
		return banner;
	}

	public void setBanner(int banner) {
		this.banner = banner;
	}

}
