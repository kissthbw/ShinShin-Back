package com.bit.model.report;

public class CatalogoTiendaCSV {
	private Integer idT;
	private Integer idP;
	private String tienda; 
	private String marca; 
	private String producto; 
	private String contenido; 
	private String departamento;
	private String tipo; 
	private String bonificacion;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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
	public Integer getIdP() {
		return idP;
	}
	public void setIdP(Integer idP) {
		this.idP = idP;
	}
	public String getTienda() {
		return tienda;
	}
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}
	public Integer getIdT() {
		return idT;
	}
	public void setIdT(Integer idT) {
		this.idT = idT;
	}
}
