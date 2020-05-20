package com.bit.model.dto;

import java.math.BigInteger;
import java.util.Date;

public class BonificacionRecargaItemCSV {

	private String usuario;
	private String tipo;
	private String company;
	private String numero;
	private Date fecha;
	private Date hora;
	private String fechaFormateada;
	private String horaFormateada;
	private Double importe;
	private String importeFormateado;

	public BonificacionRecargaItemCSV() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getFechaFormateada() {
		return fechaFormateada;
	}

	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}

	public String getHoraFormateada() {
		return horaFormateada;
	}

	public void setHoraFormateada(String horaFormateada) {
		this.horaFormateada = horaFormateada;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getImporteFormateado() {
		return importeFormateado;
	}

	public void setImporteFormateado(String importeFormateado) {
		this.importeFormateado = importeFormateado;
	}

}
