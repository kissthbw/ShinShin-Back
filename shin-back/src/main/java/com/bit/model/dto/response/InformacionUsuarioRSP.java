package com.bit.model.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_EMPTY)
public class InformacionUsuarioRSP extends SimpleResponse implements Serializable{

	private static final long serialVersionUID = 5264924332432364793L;
	
	private String nombreUsuario;
	private double bonificacion;
	private Long tickets;
	private Long retiros;
	private Long medios;
	private Usuario usuario;
	private List<MedioBonificacionUsuario> mediosBonificacion = new ArrayList<>();

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public double getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(double bonificacion) {
		this.bonificacion = bonificacion;
	}

	public Long getTickets() {
		return tickets;
	}

	public void setTickets(Long tickets) {
		this.tickets = tickets;
	}

	public Long getRetiros() {
		return retiros;
	}

	public void setRetiros(Long retiros) {
		this.retiros = retiros;
	}

	public Long getMedios() {
		return medios;
	}

	public void setMedios(Long medios) {
		this.medios = medios;
	}

	public List<MedioBonificacionUsuario> getMediosBonificacion() {
		return mediosBonificacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setMediosBonificacion(List<MedioBonificacionUsuario> mediosBonificacion) {
		this.mediosBonificacion = mediosBonificacion;
	}
	
	public void addToList(MedioBonificacionUsuario item) {
		this.mediosBonificacion.add(item);
	}
}
