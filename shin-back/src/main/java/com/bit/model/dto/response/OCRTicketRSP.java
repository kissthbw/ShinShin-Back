package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;

public class OCRTicketRSP extends SimpleResponse implements Serializable {

	private static final long serialVersionUID = -4584758005329914339L;

	private String tienda;
	private String subTienda;
	private String cpTienda;
	private String cpFiscal;
	private String fecha;
	private String hora;
	private String transaccion;
	private boolean tieneCB;
	private List<Producto> productos = new ArrayList<>();
	private List<String> lineas = new ArrayList<>();


	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getSubTienda() {
		return subTienda;
	}

	public void setSubTienda(String subTienda) {
		this.subTienda = subTienda;
	}

	public String getCpTienda() {
		return cpTienda;
	}

	public void setCpTienda(String cpTienda) {
		
		if( null != cpTienda ) {
			this.cpTienda = cpTienda.replaceAll("[^0-9]", "");
		}
		else {
			this.cpTienda = cpTienda;
		}
		
	}

	public String getCpFiscal() {
		return cpFiscal;
	}

	public void setCpFiscal(String cpFiscal) {
		if( null != cpFiscal ) {
			this.cpFiscal = cpFiscal.replaceAll("[^0-9]", "");
		}
		else {
			this.cpFiscal = cpFiscal;
		}
		
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

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public boolean isTieneCB() {
		return tieneCB;
	}

	public void setTieneCB(boolean tieneCB) {
		this.tieneCB = tieneCB;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
