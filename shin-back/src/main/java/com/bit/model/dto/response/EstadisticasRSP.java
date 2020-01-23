package com.bit.model.dto.response;

import java.util.List;

import com.bit.model.dto.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class EstadisticasRSP {

	private Integer totalUsuarios;
	private List<Item> totalUsuariosDias;
	private List<Item> totalUsuariosSemana;
	private List<Item> totalUsuariosMes;
	private Float promedioEdadUsuarios;
	private List<Item> rangoEdadUsuarios;
	private List<Item> totalUsuariosPorGenero;
	private Integer totalTickets;
	private List<Item> totalTicketsDia;
	private List<Item> totalTicketsSemana;
	private List<Item> totalTicketsMes;
	private List<Item> totalTicketsTiendaDiaHora;
	private List<Item> totalTicketsTiendaSemanaHora;
	private List<Item> totalTicketsTiendaMesHora;

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

	public List<Item> getRangoEdadUsuarios() {
		return rangoEdadUsuarios;
	}

	public void setRangoEdadUsuarios(List<Item> rangoEdadUsuarios) {
		this.rangoEdadUsuarios = rangoEdadUsuarios;
	}

	public List<Item> getTotalUsuariosPorGenero() {
		return totalUsuariosPorGenero;
	}

	public void setTotalUsuariosPorGenero(List<Item> totalUsuariosPorGenero) {
		this.totalUsuariosPorGenero = totalUsuariosPorGenero;
	}

	public Integer getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(Integer totalTickets) {
		this.totalTickets = totalTickets;
	}

	public List<Item> getTotalTicketsDia() {
		return totalTicketsDia;
	}

	public void setTotalTicketsDia(List<Item> totalTicketsDia) {
		this.totalTicketsDia = totalTicketsDia;
	}

	public List<Item> getTotalTicketsSemana() {
		return totalTicketsSemana;
	}

	public void setTotalTicketsSemana(List<Item> totalTicketsSemana) {
		this.totalTicketsSemana = totalTicketsSemana;
	}

	public List<Item> getTotalTicketsMes() {
		return totalTicketsMes;
	}

	public void setTotalTicketsMes(List<Item> totalTicketsMes) {
		this.totalTicketsMes = totalTicketsMes;
	}

	public List<Item> getTotalTicketsTiendaDiaHora() {
		return totalTicketsTiendaDiaHora;
	}

	public void setTotalTicketsTiendaDiaHora(List<Item> totalTicketsTiendaDiaHora) {
		this.totalTicketsTiendaDiaHora = totalTicketsTiendaDiaHora;
	}

	public List<Item> getTotalTicketsTiendaSemanaHora() {
		return totalTicketsTiendaSemanaHora;
	}

	public void setTotalTicketsTiendaSemanaHora(List<Item> totalTicketsTiendaSemanaHora) {
		this.totalTicketsTiendaSemanaHora = totalTicketsTiendaSemanaHora;
	}

	public List<Item> getTotalTicketsTiendaMesHora() {
		return totalTicketsTiendaMesHora;
	}

	public void setTotalTicketsTiendaMesHora(List<Item> totalTicketsTiendaMesHora) {
		this.totalTicketsTiendaMesHora = totalTicketsTiendaMesHora;
	}

}
