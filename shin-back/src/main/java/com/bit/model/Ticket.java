package com.bit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date hora;

	@Column(name = "subtotal")
	private double subtotal;

	@Column(name = "iva")
	private double iva;

	@Column(name = "total")
	private double total;
	
	//Obtener historico bonificaciones
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "historico_bonificaciones", joinColumns = {
			@JoinColumn(name = "id_ticket") },
			inverseJoinColumns = { @JoinColumn(name = "producto_id_producto") })
	private List<Producto> productos = new ArrayList<>();

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

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Date getHora() {
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

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	
	public void addProducto(Producto producto) {
		productos.add(producto);
	}

}
