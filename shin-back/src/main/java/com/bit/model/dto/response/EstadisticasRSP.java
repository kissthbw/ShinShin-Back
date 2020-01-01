package com.bit.model.dto.response;

import java.util.List;

public class EstadisticasRSP {

	private Integer totalUsuarios;
	private List<Item> totalUsuariosDias;
	private List<Item> totalUsuariosSemana;
	private List<Item> totalUsuariosMes;
	private Float promedioEdadUsuarios;
	private List<Item> totalUsuariosPorGenero;

	public Integer getTotalUsuarios() {
		return totalUsuarios;
	}

	public void setTotalUsuarios(Integer totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}

	public List<Item> getTotalUsuariosDias() {
		return totalUsuariosDias;
	}

	public void setTotalUsuariosDias(List<Item> totalUsuariosDias) {
		this.totalUsuariosDias = totalUsuariosDias;
	}

	public List<Item> getTotalUsuariosSemana() {
		return totalUsuariosSemana;
	}

	public void setTotalUsuariosSemana(List<Item> totalUsuariosSemana) {
		this.totalUsuariosSemana = totalUsuariosSemana;
	}

	public List<Item> getTotalUsuariosMes() {
		return totalUsuariosMes;
	}

	public void setTotalUsuariosMes(List<Item> totalUsuariosMes) {
		this.totalUsuariosMes = totalUsuariosMes;
	}

	public Float getPromedioEdadUsuarios() {
		return promedioEdadUsuarios;
	}

	public void setPromedioEdadUsuarios(Float promedioEdadUsuarios) {
		this.promedioEdadUsuarios = promedioEdadUsuarios;
	}

	public List<Item> getTotalUsuariosPorGenero() {
		return totalUsuariosPorGenero;
	}

	public void setTotalUsuariosPorGenero(List<Item> totalUsuariosPorGenero) {
		this.totalUsuariosPorGenero = totalUsuariosPorGenero;
	}

}
