package com.bit.model.dto;

import java.math.BigInteger;
import java.util.Date;

public class BonificacionItem {

	private String id;
	private Integer idTipo;
	private String tipo;
	private String company;
	private Date fecha;
	private Date hora;
	private String fechaFormateada;
	private String horaFormateada;
	private BigInteger solicitudes;
	private Double importe;
	private String importeFormateado;
	private Integer idUsuario;

	public BonificacionItem() {
	}

	public BonificacionItem(String id, Integer idTipo, String tipo, Date fecha, Date hora, BigInteger solicitudes,
			Double importe, Integer idUsuario) {
		super();
		this.id = id;
		this.idTipo = idTipo;
		this.tipo = tipo;
		this.fecha = fecha;
		this.hora = hora;
		this.solicitudes = solicitudes;
		this.importe = importe;
		this.idUsuario = idUsuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "BonificacionItem [" + (id != null ? "id=" + id + ", " : "")
				+ (idTipo != null ? "idTipo=" + idTipo + ", " : "") + (tipo != null ? "tipo=" + tipo + ", " : "")
				+ (fecha != null ? "fecha=" + fecha + ", " : "") + (hora != null ? "hora=" + hora + ", " : "")
				+ (fechaFormateada != null ? "fechaFormateada=" + fechaFormateada + ", " : "")
				+ (horaFormateada != null ? "horaFormateada=" + horaFormateada + ", " : "")
				+ (solicitudes != null ? "solicitudes=" + solicitudes + ", " : "")
				+ (importe != null ? "importe=" + importe + ", " : "")
				+ (importeFormateado != null ? "importeFormateado=" + importeFormateado + ", " : "")
				+ (idUsuario != null ? "idUsuario=" + idUsuario : "") + "]";
	}

}
