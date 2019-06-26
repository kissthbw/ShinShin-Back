package com.bit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "historico_medios_bonificacion")
public class HistoricoMediosBonificacion {

	@Id
	@Column(name = "id_historico_medios_bonificacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHistoricoMediosBonificacion;

	@Column(name = "fecha_bonificacion")
	@Temporal(TemporalType.DATE)
	private Date fechaBonificacion;

	@Column(name = "hora_bonificacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaBonificacion;

	@Column(name = "cantidad_bonificacion")
	private double cantidadBonificacion;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medios_bonificacion")
	private MediosBonificacion mediosBonificacion;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario;

	public void setIdHistoricoMediosBonificacion(Long idHistoricoMediosBonificacion) {
		this.idHistoricoMediosBonificacion = idHistoricoMediosBonificacion;
	}

	public Long getIdHistoricoMediosBonificacion() {
		return idHistoricoMediosBonificacion;
	}

	public void setFechaBonificacion(Date fechaBonificacion) {
		this.fechaBonificacion = fechaBonificacion;
	}

	public Date getFechaBonificacion() {
		return fechaBonificacion;
	}

	public void setHoraBonificacion(Date horaBonificacion) {
		this.horaBonificacion = horaBonificacion;
	}

	public Date getHoraBonificacion() {
		return horaBonificacion;
	}

	public void setCantidadBonificacion(double cantidadBonificacion) {
		this.cantidadBonificacion = cantidadBonificacion;
	}

	public double getCantidadBonificacion() {
		return cantidadBonificacion;
	}

	public void setMediosBonificacion(MediosBonificacion mediosBonificacion) {
		this.mediosBonificacion = mediosBonificacion;
	}

	public MediosBonificacion getMediosBonificacion() {
		return mediosBonificacion;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
