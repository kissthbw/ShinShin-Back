package com.bit.model.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoBancaria;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Producto;
import com.bit.model.ProductoValoracion;
import com.bit.model.ProductosTiendas;
import com.bit.model.Proveedor;
import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class ListItemsRSP extends SimpleResponse {

	private static final long serialVersionUID = 4428570789013267038L;

	// Campos usados para la paginacion
	private Long total;
	private Long page;
	private Long pages;
	private Boolean hasMore;

	private List<Producto> productos = new ArrayList<>();
	private List<Proveedor> proveedores = new ArrayList<>();
	private List<CatalogoMarca> marcas;
	private List<CatalogoMediosBonificacion> mediosBonificacion;
	private List<CatalogoTipoBancaria> tiposBancarias;
	private List<CatalogoTienda> tiendas;
	private List<CatalogoTipoProducto> tipoProductos;
	private List<HistoricoMediosBonificacion> historicoMediosBonificaciones;
	private List<MediosBonificacion> mediosBonificaciones;
	private List<ProductoValoracion> productoValoraciones;
	private List<Ticket> tickets;
	private List<Usuario> usuarios;
	private List<ProductosTiendas> productosTienda;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}

	public Boolean getHasMore() {
		return hasMore;
	}

	public void setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
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

	public List<CatalogoTipoBancaria> getTiposBancarias() {
		return tiposBancarias;
	}

	public void setTiposBancarias(List<CatalogoTipoBancaria> tiposBancarias) {
		this.tiposBancarias = tiposBancarias;
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

	public List<ProductosTiendas> getProductosTienda() {
		return productosTienda;
	}

	public void setProductosTienda(List<ProductosTiendas> productosTienda) {
		this.productosTienda = productosTienda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
