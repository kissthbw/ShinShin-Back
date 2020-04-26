package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bit.model.Producto;
import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.SimpleResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class InformacionDashboardProveedorRSP extends SimpleResponse implements Serializable {

	private static final long serialVersionUID = 5264924332432364793L;

	private Long totalUsuarios;
	private Double totalBonificaciones;
	private Long totalProductos;
	
	//Para seccion de productos
	private Long totalProductosEscaneados;
	private List<Producto> productos = new ArrayList<>();
	
	//Para seccion de usuarios
	private String porcentajeH;
	private String porcentajeM;
	private String promedioEdad;
	private List<Usuario> usuarios = new ArrayList<>();
	
	//Para seccion finanzas
	private List<BonificacionItem> bonificaciones = new ArrayList<>();
	
	public Long getTotalUsuarios() {
		return totalUsuarios;
	}

	public void setTotalUsuarios(Long totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}

	public Double getTotalBonificaciones() {
		return totalBonificaciones;
	}

	public void setTotalBonificaciones(Double totalBonificaciones) {
		this.totalBonificaciones = totalBonificaciones;
	}

	public Long getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(Long totalProductos) {
		this.totalProductos = totalProductos;
	}

	public Long getTotalProductosEscaneados() {
		return totalProductosEscaneados;
	}

	public void setTotalProductosEscaneados(Long totalProductosEscaneados) {
		this.totalProductosEscaneados = totalProductosEscaneados;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getPorcentajeH() {
		return porcentajeH;
	}

	public void setPorcentajeH(String porcentajeH) {
		this.porcentajeH = porcentajeH;
	}

	public String getPorcentajeM() {
		return porcentajeM;
	}

	public void setPorcentajeM(String porcentajeM) {
		this.porcentajeM = porcentajeM;
	}

	public String getPromedioEdad() {
		return promedioEdad;
	}

	public void setPromedioEdad(String promedioEdad) {
		this.promedioEdad = promedioEdad;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<BonificacionItem> getBonificaciones() {
		return bonificaciones;
	}

	public void setBonificaciones(List<BonificacionItem> bonificaciones) {
		this.bonificaciones = bonificaciones;
	}

}
