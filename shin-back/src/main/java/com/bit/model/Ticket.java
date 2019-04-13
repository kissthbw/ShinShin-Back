package com.bit.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "ticket")
public class Ticket {

	@Id
	@Column(name = "id_ticket")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTicket;

	@Column(name = "nombre_tienda")
	private String nombreTienda;

	@Column(name = "sucursal")
	private String sucursal;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "hora")
	private Time hora;

	@Column(name = "subtotal")
	private double subtotal;

	@Column(name = "iva")
	private double iva;

	@Column(name = "total")
	private double total;

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Time getHora() {
		return hora;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getIva() {
		return iva;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

}
