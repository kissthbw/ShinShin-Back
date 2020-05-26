package com.bit.model.report;

import java.io.Serializable;

public class RetirosUsuarioReport implements Serializable {

	private static final long serialVersionUID = 4894009613793732727L;

	private Long idUsuario;
	private String usuario;
	private String nombre;
	private String fecha;
	private String retirosBancarios;
	private String retirosPayPal;
	private String recargas;

	public RetirosUsuarioReport() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getRetirosBancarios() {
		return retirosBancarios;
	}

	public void setRetirosBancarios(String retirosBancarios) {
		this.retirosBancarios = retirosBancarios;
	}

	public String getRetirosPayPal() {
		return retirosPayPal;
	}

	public void setRetirosPayPal(String retirosPayPal) {
		this.retirosPayPal = retirosPayPal;
	}

	public String getRecargas() {
		return recargas;
	}

	public void setRecargas(String recargas) {
		this.recargas = recargas;
	}

}
