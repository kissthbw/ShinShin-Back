package com.bit.model.report;

public class UsuarioReport {
	private Long idUsuario;
	private String usuario;
	private String estado;
	private String estatusActivacion;

	public UsuarioReport(Long idUsuario, String usuario, String estado, String estatusActivacion) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.estado = estado;
		this.estatusActivacion = estatusActivacion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstatusActivacion() {
		return estatusActivacion;
	}

	public void setEstatusActivacion(String estatusActivacion) {
		this.estatusActivacion = estatusActivacion;
	}

}
