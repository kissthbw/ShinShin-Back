package com.bit.model.report;

public class MarcaReport {
	private Long idCatalogoMarca;
	private String nombreMarca;

	public MarcaReport(Long idCatalogoMarca, String nombreMarca) {
		this.idCatalogoMarca = idCatalogoMarca;
		this.nombreMarca = nombreMarca;

	}

	public Long getIdCatalogoMarca() {
		return idCatalogoMarca;
	}

	public void setIdCatalogoMarca(Long idCatalogoMarca) {
		this.idCatalogoMarca = idCatalogoMarca;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

}
