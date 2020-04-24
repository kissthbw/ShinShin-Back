package com.bit.model.dto.response;

import java.io.Serializable;

import com.bit.model.dto.SimpleResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class InformacionDashboardProveedorRSP extends SimpleResponse implements Serializable {

	private static final long serialVersionUID = 5264924332432364793L;

	private Long totalUsuarios;
	private Double totalBonificaciones;
	private Long totalProductos;

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

}
