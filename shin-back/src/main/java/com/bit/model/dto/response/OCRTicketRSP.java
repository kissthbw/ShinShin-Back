package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;

public class OCRTicketRSP extends SimpleResponse implements Serializable {

	private static final long serialVersionUID = -4584758005329914339L;

	private String tienda;
	private String fecha;
	private String hora;
	private List<Producto> productos = new ArrayList<>();
	private List<String> lineas = new ArrayList<>();


	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public List<String> getLineas() {
		return lineas;
	}

	public void setLineas(List<String> lineas) {
		this.lineas = lineas;
	}
}
