package com.bit.model.dto.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class EstadisticaGeneralCSV {

	private Date fecha; 
	
	private BigInteger totalUsuarios;
	private BigDecimal totalEdadPromedio;
	
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

	

}
