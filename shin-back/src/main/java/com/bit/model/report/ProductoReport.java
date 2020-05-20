package com.bit.model.report;

public class ProductoReport {
	private Long idProducto;
	private String contenido;
	private String depto;
	private String tipo;
	private	String bonificacion;
	private String codigoBarras;
	private String nombreProducto;
	private String marca;
	private int banner;

	public ProductoReport(String nombreProducto, String contenido, String marca, String depto, String tipo, String bonificacion) {
		this.nombreProducto = nombreProducto;
		this.contenido=contenido;
		this.marca = marca;
		this.depto=depto;
		this.tipo=tipo;
		this.bonificacion=bonificacion;
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

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}

}
