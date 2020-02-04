package com.bit.model.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.bit.model.dto.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class EstadisticasBonificacionRSP {

	private Integer totalDepositos;
	private Double totalBonificaciones;
	private Integer totalRecargas;

	// Seccion de graficas
	// Depositos
	private List<Item> depositos = new ArrayList<>();

	// Recargas
	private List<Item> recargas = new ArrayList<>();

	// Bonificaciones totales
	private List<Item> bonificaciones = new ArrayList<>();

	public Integer getTotalDepositos() {
		return totalDepositos;
	}

	public void setTotalDepositos(Integer totalDepositos) {
		this.totalDepositos = totalDepositos;
	}

	public Double getTotalBonificaciones() {
		return totalBonificaciones;
	}

	public void setTotalBonificaciones(Double totalBonificaciones) {
		this.totalBonificaciones = totalBonificaciones;
	}

	public Integer getTotalRecargas() {
		return totalRecargas;
	}

	public void setTotalRecargas(Integer totalRecargas) {
		this.totalRecargas = totalRecargas;
	}

	public List<Item> getDepositos() {
		return depositos;
	}

	public void setDepositos(List<Item> depositos) {
		this.depositos = depositos;
	}

	public List<Item> getRecargas() {
		return recargas;
	}

	public void setRecargas(List<Item> recargas) {
		this.recargas = recargas;
	}

	public List<Item> getBonificaciones() {
		return bonificaciones;
	}

	public void setBonificaciones(List<Item> bonificaciones) {
		this.bonificaciones = bonificaciones;
	}

}
