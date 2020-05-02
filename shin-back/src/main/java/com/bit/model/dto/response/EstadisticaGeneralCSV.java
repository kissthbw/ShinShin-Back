package com.bit.model.dto.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bit.model.report.EstadisticaGeneralTotalTicketCSV;

/**
 * Representa un registro en el CSV de estadistica general.
 *  
 * @author guichosun
 */
public class EstadisticaGeneralCSV {

	private Date fecha; 
	
	private BigInteger totalUsuarios;
	private BigDecimal totalEdadPromedio;
	
	private BigInteger avg1824;
	private BigInteger avg2529;
	private BigInteger avg3039;
	private BigInteger avg4049;
	private BigInteger avg5059;
	private BigInteger avgMAS60;
	private BigInteger mujeres;
	private BigInteger hombres;
	
	// las estadisticas de una tienda
	private BigInteger totalEscaneadosTiendas;
	private List<EstadisticaGeneralTotalTicketCSV> totalesDeTiendas = new ArrayList<EstadisticaGeneralTotalTicketCSV>();
	
	// las estadisticas de un depto
	private BigInteger totalProductosEscaneadosDesptos;
	private List<EstadisticaGeneralTotalTicketCSV> totalesDeDeptos = new ArrayList<EstadisticaGeneralTotalTicketCSV>();
	
	// las estadisticas por marca
	private BigInteger totalProductosEscaneadosMarcas;
	private List<EstadisticaGeneralTotalTicketCSV> totalesDeMarcas = new ArrayList<EstadisticaGeneralTotalTicketCSV>();
	
	public BigInteger getTotalUsuarios() {
		return totalUsuarios;
	}

	public void setTotalUsuarios(BigInteger totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}

	public BigDecimal getTotalEdadPromedio() {
		return totalEdadPromedio;
	}

	public void setTotalEdadPromedio(BigDecimal totalEdadPromedio) {
		this.totalEdadPromedio = totalEdadPromedio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigInteger getAvg1824() {
		return avg1824;
	}

	public void setAvg1824(BigInteger avg1824) {
		this.avg1824 = avg1824;
	}

	public BigInteger getAvg2529() {
		return avg2529;
	}

	public void setAvg2529(BigInteger avg2529) {
		this.avg2529 = avg2529;
	}

	public BigInteger getAvg3039() {
		return avg3039;
	}

	public void setAvg3039(BigInteger avg3039) {
		this.avg3039 = avg3039;
	}

	public BigInteger getAvg4049() {
		return avg4049;
	}

	public void setAvg4049(BigInteger avg4049) {
		this.avg4049 = avg4049;
	}

	public BigInteger getAvg5059() {
		return avg5059;
	}

	public void setAvg5059(BigInteger avg5059) {
		this.avg5059 = avg5059;
	}

	public BigInteger getAvgMAS60() {
		return avgMAS60;
	}

	public void setAvgMAS60(BigInteger avgMAS60) {
		this.avgMAS60 = avgMAS60;
	}

	public BigInteger getMujeres() {
		return mujeres;
	}

	public void setMujeres(BigInteger mujeres) {
		this.mujeres = mujeres;
	}

	public BigInteger getHombres() {
		return hombres;
	}

	public void setHombres(BigInteger hombres) {
		this.hombres = hombres;
	}

	public BigInteger getTotalEscaneadosTiendas() {
		return totalEscaneadosTiendas;
	}

	public void setTotalEscaneadosTiendas(BigInteger totalEscaneadosTiendas) {
		this.totalEscaneadosTiendas = totalEscaneadosTiendas;
	}

	public List<EstadisticaGeneralTotalTicketCSV> getTotalesDeTiendas() {
		return totalesDeTiendas;
	}

	public void setTotalesDeTiendas(List<EstadisticaGeneralTotalTicketCSV> totalesDeTiendas) {
		this.totalesDeTiendas = totalesDeTiendas;
	}

	public List<EstadisticaGeneralTotalTicketCSV> getTotalesDeDeptos() {
		return totalesDeDeptos;
	}

	public void setTotalesDeDeptos(List<EstadisticaGeneralTotalTicketCSV> totalesDeDeptos) {
		this.totalesDeDeptos = totalesDeDeptos;
	}

	public BigInteger getTotalProductosEscaneadosDesptos() {
		return totalProductosEscaneadosDesptos;
	}

	public void setTotalProductosEscaneadosDesptos(BigInteger totalProductosEscaneadosDesptos) {
		this.totalProductosEscaneadosDesptos = totalProductosEscaneadosDesptos;
	}

	public BigInteger getTotalProductosEscaneadosMarcas() {
		return totalProductosEscaneadosMarcas;
	}

	public void setTotalProductosEscaneadosMarcas(BigInteger totalProductosEscaneadosMarcas) {
		this.totalProductosEscaneadosMarcas = totalProductosEscaneadosMarcas;
	}

	public List<EstadisticaGeneralTotalTicketCSV> getTotalesDeMarcas() {
		return totalesDeMarcas;
	}

	public void setTotalesDeMarcas(List<EstadisticaGeneralTotalTicketCSV> totalesDeMarcas) {
		this.totalesDeMarcas = totalesDeMarcas;
	}
}
