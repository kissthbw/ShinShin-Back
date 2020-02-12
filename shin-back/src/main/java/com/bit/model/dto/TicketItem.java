package com.bit.model.dto;

import java.math.BigInteger;
import java.util.Date;

public class TicketItem {

	private Integer id;
	private String identificador;
	private Integer idUsuario;
	private String tienda;
	private Date fecha;
	private String fechaFormateada;
	private Date hora;
	private String horaFormateada;
	private BigInteger cantidad;
	private Double importe;
	private String importeFormateado;

	public TicketItem() {}
	
	public TicketItem(Integer id, String identificador, Integer idUsuario, String tienda, Date fecha,
			String fechaFormateada, Date hora, String horaFormateada, BigInteger cantidad, Double importe,
			String importeFormateado) {
		super();
		this.id = id;
		this.identificador = identificador;
		this.idUsuario = idUsuario;
		this.tienda = tienda;
		this.fecha = fecha;
		this.fechaFormateada = fechaFormateada;
		this.hora = hora;
		this.horaFormateada = horaFormateada;
		this.cantidad = cantidad;
		this.importe = importe;
		this.importeFormateado = importeFormateado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFechaFormateada() {
		return fechaFormateada;
	}

	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getHoraFormateada() {
		return horaFormateada;
	}

	public void setHoraFormateada(String horaFormateada) {
		this.horaFormateada = horaFormateada;
	}

	public BigInteger getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigInteger cantidad) {
		this.cantidad = cantidad;
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

	@Override
	public String toString() {
		return "TicketItem [" + (id != null ? "id=" + id + ", " : "")
				+ (identificador != null ? "identificador=" + identificador + ", " : "")
				+ (idUsuario != null ? "idUsuario=" + idUsuario + ", " : "")
				+ (tienda != null ? "tienda=" + tienda + ", " : "") + (fecha != null ? "fecha=" + fecha + ", " : "")
				+ (fechaFormateada != null ? "fechaFormateada=" + fechaFormateada + ", " : "")
				+ (hora != null ? "hora=" + hora + ", " : "")
				+ (horaFormateada != null ? "horaFormateada=" + horaFormateada + ", " : "")
				+ (cantidad != null ? "cantidad=" + cantidad + ", " : "")
				+ (importe != null ? "importe=" + importe + ", " : "")
				+ (importeFormateado != null ? "importeFormateado=" + importeFormateado : "") + "]";
	}

}
