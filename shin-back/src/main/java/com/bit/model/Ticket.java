package com.bit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import com.bit.model.dto.ImageItem;

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
	
	//Datos del ticket escaneado
	//tienda
	@Column(name = "ticket_tienda")
	private String ticket_tienda;
	
	//subtienda
	@Column(name = "ticket_subtienda")
	private String ticket_subTienda;
	
	//transaccion
	@Column(name = "ticket_transaccion")
	private String ticket_transaccion;
	
	//fecha
	@Column(name = "ticket_fecha")
	private String ticket_fecha;
	
	//hora
	@Column(name = "ticket_hora")
	private String ticket_hora;
	
	//Obtener historico bonificaciones
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "historico_bonificaciones", joinColumns = {
			@JoinColumn(name = "id_ticket") },
			inverseJoinColumns = { @JoinColumn(name = "producto_id_producto") })
	private List<Producto> productos = new ArrayList<>();
	
	@Transient
	private String totalBonificacionFormateada;
	
	@Transient
	private String formatFecha;
	
	@Transient
	private int totalProductos;
	
	@Transient
	private List<ImageItem> ticketPhotos;

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

	public String getTicket_tienda() {
		return ticket_tienda;
	}

	public void setTicket_tienda(String ticket_tienda) {
		this.ticket_tienda = ticket_tienda;
	}

	public String getTicket_subTienda() {
		return ticket_subTienda;
	}

	public void setTicket_subTienda(String ticket_subTienda) {
		this.ticket_subTienda = ticket_subTienda;
	}

	public String getTicket_transaccion() {
		return ticket_transaccion;
	}

	public void setTicket_transaccion(String ticket_transaccion) {
		this.ticket_transaccion = ticket_transaccion;
	}

	public String getTicket_fecha() {
		return ticket_fecha;
	}

	public void setTicket_fecha(String ticket_fecha) {
		this.ticket_fecha = ticket_fecha;
	}

	public String getTicket_hora() {
		return ticket_hora;
	}

	public void setTicket_hora(String ticket_hora) {
		this.ticket_hora = ticket_hora;
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

	public String getTotalBonificacionFormateada() {
		return totalBonificacionFormateada;
	}

	public void setTotalBonificacionFormateada(String totalBonificacionFormateada) {
		this.totalBonificacionFormateada = totalBonificacionFormateada;
	}

	public String getFormatFecha() {
		return formatFecha;
	}

	public void setFormatFecha(String formatFecha) {
		this.formatFecha = formatFecha;
	}

	public int getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(int totalProductos) {
		this.totalProductos = totalProductos;
	}

	public List<ImageItem> getTicketPhotos() {
		return ticketPhotos;
	}

	public void setTicketPhotos(List<ImageItem> ticketPhotos) {
		this.ticketPhotos = ticketPhotos;
	}

}
