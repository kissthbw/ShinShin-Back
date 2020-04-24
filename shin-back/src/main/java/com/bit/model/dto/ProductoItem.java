package com.bit.model.dto;

import java.math.BigInteger;
import java.util.Date;

public class ProductoItem {

	private Long id;
	private String nombre;
	private Date fecha;
	private Date hora;
	private String fechaFormateada;
	private String horaFormateada;
	private BigInteger solicitudes;
	private Double bonificacion;
	private String bonificacionFormateada;

	public ProductoItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public BigInteger getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(BigInteger solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Double getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Double bonificacion) {
		this.bonificacion = bonificacion;
	}

	public String getBonificacionFormateada() {
		return bonificacionFormateada;
	}

	public void setBonificacionFormateada(String bonificacionFormateada) {
		this.bonificacionFormateada = bonificacionFormateada;
	}

	@Override
	public String toString() {
		return "ProductoItem [" + (id != null ? "id=" + id + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "") + (fecha != null ? "fecha=" + fecha + ", " : "")
				+ (hora != null ? "hora=" + hora + ", " : "")
				+ (fechaFormateada != null ? "fechaFormateada=" + fechaFormateada + ", " : "")
				+ (horaFormateada != null ? "horaFormateada=" + horaFormateada + ", " : "")
				+ (solicitudes != null ? "solicitudes=" + solicitudes + ", " : "")
				+ (bonificacion != null ? "bonificacion=" + bonificacion + ", " : "")
				+ (bonificacionFormateada != null ? "importeFormateado=" + bonificacionFormateada : "") + "]";
	}

}
