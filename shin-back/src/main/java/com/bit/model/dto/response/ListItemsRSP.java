package com.bit.model.dto.response;

import java.util.List;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Producto;
import com.bit.model.ProductoValoracion;
import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;

public class ListItemsRSP extends SimpleResponse{

	private static final long serialVersionUID = 4428570789013267038L;
	
	private List<Producto> productos;
	private List<CatalogoMarca> marcas;
	private List<CatalogoMediosBonificacion> mediosBonificacion;
	private List<CatalogoTienda> tiendas;
	private List<CatalogoTipoProducto> tipoProductos;
	private List<HistoricoMediosBonificacion> historicoMediosBonificaciones;
	private List<MediosBonificacion> mediosBonificaciones;
	private List<ProductoValoracion> productoValoraciones;
	private List<Ticket> tickets;
	private List<Usuario> usuarios;

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<CatalogoMarca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<CatalogoMarca> marcas) {
		this.marcas = marcas;
	}

	public List<CatalogoMediosBonificacion> getMediosBonificacion() {
		return mediosBonificacion;
	}

	public void setMediosBonificacion(List<CatalogoMediosBonificacion> mediosBonificacion) {
		this.mediosBonificacion = mediosBonificacion;
	}

	public List<CatalogoTienda> getTiendas() {
		return tiendas;
	}

	public void setTiendas(List<CatalogoTienda> tiendas) {
		this.tiendas = tiendas;
	}

	public List<CatalogoTipoProducto> getTipoProductos() {
		return tipoProductos;
	}

	public void setTipoProductos(List<CatalogoTipoProducto> tipoProductos) {
		this.tipoProductos = tipoProductos;
	}

	public List<HistoricoMediosBonificacion> getHistoricoMediosBonificaciones() {
		return historicoMediosBonificaciones;
	}

	public void setHistoricoMediosBonificaciones(List<HistoricoMediosBonificacion> historicoMediosBonificaciones) {
		this.historicoMediosBonificaciones = historicoMediosBonificaciones;
	}

	public List<MediosBonificacion> getMediosBonificaciones() {
		return mediosBonificaciones;
	}

	public void setMediosBonificaciones(List<MediosBonificacion> mediosBonificaciones) {
		this.mediosBonificaciones = mediosBonificaciones;
	}

	public List<ProductoValoracion> getProductoValoraciones() {
		return productoValoraciones;
	}

	public void setProductoValoraciones(List<ProductoValoracion> productoValoraciones) {
		this.productoValoraciones = productoValoraciones;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
