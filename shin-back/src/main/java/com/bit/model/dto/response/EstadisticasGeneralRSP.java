package com.bit.model.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class EstadisticasGeneralRSP {

	private Integer totalUsuarios;
	private Integer totalTicketsEscaneados;
	private Integer totalProductosEscaneados;
	private Integer totalDepartamentos;
	// Usuarios
	private List<Item> totalUsuariosDias;
	private List<Item> totalUsuariosSemana;
	private List<Item> totalUsuariosMes;
	// Tickets Escaneados
	private List<Item> totalEscaneosDias;
	private List<Item> totalEscaneosSemana;
	private List<Item> totalEscaneosMes;
	// Productos escaneos
	private List<Item> totalProductosEscaneadosDias;
	private List<Item> totalProductosEscaneadosSemana;
	private List<Item> totalProductosEscaneadosMes;
	// Escaneos por tienda
	private List<Category> totalEscaneaosTiendaMes;

	public Integer getTotalUsuarios() {
		return totalUsuarios;
	}

	public void setTotalUsuarios(Integer totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}

	public Integer getTotalTicketsEscaneados() {
		return totalTicketsEscaneados;
	}

	public void setTotalTicketsEscaneados(Integer totalTicketsEscaneados) {
		this.totalTicketsEscaneados = totalTicketsEscaneados;
	}

	public Integer getTotalProductosEscaneados() {
		return totalProductosEscaneados;
	}

	public void setTotalProductosEscaneados(Integer totalProductosEscaneados) {
		this.totalProductosEscaneados = totalProductosEscaneados;
	}

	public Integer getTotalDepartamentos() {
		return totalDepartamentos;
	}

	public void setTotalDepartamentos(Integer totalDepartamentos) {
		this.totalDepartamentos = totalDepartamentos;
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

	public List<Item> getTotalEscaneosDias() {
		return totalEscaneosDias;
	}

	public void setTotalEscaneosDias(List<Item> totalEscaneosDias) {
		this.totalEscaneosDias = totalEscaneosDias;
	}

	public List<Item> getTotalEscaneosSemana() {
		return totalEscaneosSemana;
	}

	public void setTotalEscaneosSemana(List<Item> totalEscaneosSemana) {
		this.totalEscaneosSemana = totalEscaneosSemana;
	}

	public List<Item> getTotalEscaneosMes() {
		return totalEscaneosMes;
	}

	public void setTotalEscaneosMes(List<Item> totalEscaneosMes) {
		this.totalEscaneosMes = totalEscaneosMes;
	}

	public List<Item> getTotalProductosEscaneadosDias() {
		return totalProductosEscaneadosDias;
	}

	public void setTotalProductosEscaneadosDias(List<Item> totalProductosEscaneadosDias) {
		this.totalProductosEscaneadosDias = totalProductosEscaneadosDias;
	}

	public List<Item> getTotalProductosEscaneadosSemana() {
		return totalProductosEscaneadosSemana;
	}

	public void setTotalProductosEscaneadosSemana(List<Item> totalProductosEscaneadosSemana) {
		this.totalProductosEscaneadosSemana = totalProductosEscaneadosSemana;
	}

	public List<Item> getTotalProductosEscaneadosMes() {
		return totalProductosEscaneadosMes;
	}

	public void setTotalProductosEscaneadosMes(List<Item> totalProductosEscaneadosMes) {
		this.totalProductosEscaneadosMes = totalProductosEscaneadosMes;
	}

	public List<Category> getTotalEscaneaosTiendaMes() {
		return totalEscaneaosTiendaMes;
	}

	public void setTotalEscaneaosTiendaMes(List<Category> totalEscaneaosTiendaMes) {
		this.totalEscaneaosTiendaMes = totalEscaneaosTiendaMes;
	}

}
