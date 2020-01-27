package com.bit.model.dto.response;

import java.util.List;

import com.bit.model.dto.Category;
import com.bit.model.dto.Item;
import com.bit.model.dto.ResumenItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class EstadisticasGeneralRSP {

	private Integer totalUsuarios;
	private Integer totalTicketsEscaneados;
	private Integer totalProductosEscaneados;
	private Integer totalDepartamentos;
	private Integer totalMarcas;
	private Integer totalProductos;

	// Seccion de graficas
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

	private List<Item> listaTopMarcas;
	private List<Item> listaTopDeptos;

	// Escaneos por departamento
	private List<Category> totalEscaneaosDepartamentoMes;

	// Escaneos por tienda
	private List<Category> totalEscaneaosTiendaMes;

	// Historicos
	// Resumen departamentos
	private List<ResumenItem> listaResumenDepartamentos;

	// Resumen tiendas
	private List<ResumenItem> listaResumenTiendas;

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

	public Integer getTotalMarcas() {
		return totalMarcas;
	}

	public void setTotalMarcas(Integer totalMarcas) {
		this.totalMarcas = totalMarcas;
	}

	public Integer getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(Integer totalProductos) {
		this.totalProductos = totalProductos;
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

	public List<Item> getListaTopMarcas() {
		return listaTopMarcas;
	}

	public void setListaTopMarcas(List<Item> listaTopMarcas) {
		this.listaTopMarcas = listaTopMarcas;
	}

	public List<Item> getListaTopDeptos() {
		return listaTopDeptos;
	}

	public void setListaTopDeptos(List<Item> listaTopDeptos) {
		this.listaTopDeptos = listaTopDeptos;
	}

	public List<Category> getTotalEscaneaosDepartamentoMes() {
		return totalEscaneaosDepartamentoMes;
	}

	public void setTotalEscaneaosDepartamentoMes(List<Category> totalEscaneaosDepartamentoMes) {
		this.totalEscaneaosDepartamentoMes = totalEscaneaosDepartamentoMes;
	}

	public List<Category> getTotalEscaneaosTiendaMes() {
		return totalEscaneaosTiendaMes;
	}

	public void setTotalEscaneaosTiendaMes(List<Category> totalEscaneaosTiendaMes) {
		this.totalEscaneaosTiendaMes = totalEscaneaosTiendaMes;
	}

	public List<ResumenItem> getListaResumenDepartamentos() {
		return listaResumenDepartamentos;
	}

	public void setListaResumenDepartamentos(List<ResumenItem> listaResumenDepartamentos) {
		this.listaResumenDepartamentos = listaResumenDepartamentos;
	}

	public List<ResumenItem> getListaResumenTiendas() {
		return listaResumenTiendas;
	}

	public void setListaResumenTiendas(List<ResumenItem> listaResumenTiendas) {
		this.listaResumenTiendas = listaResumenTiendas;
	}

}
