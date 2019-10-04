package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "medios_bonificacion")
public class MediosBonificacion {

	@Id
	@Column(name = "id_medios_bonificacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMediosBonificacion;

	@Column(name = "alias_medio_bonificacion")
	private String aliasMedioBonificacion;

	@Column(name = "cuenta_medio_bonificacion")
	private String cuentaMedioBonificacion;

	@Column(name = "compania_medio_bonificacion")
	private String companiaMedioBonificacion;

	@Column(name = "vigencia_medio_bonificacion")
	private String vigenciaMedioBonificacion;

	@Column(name = "id_cuenta_medio_bonificacion")
	private String idCuentaMedioBonificacion;
	
	@Column(name = "id_tipo")
	private Integer idTipo;
	
	@Column(name = "estatus")
	private Integer estatus;
	
	@Column(name = "banco")
	private String banco;

	// Guardar medio de bonificacion
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_catalogo_medio_bonificacion")
	private CatalogoMediosBonificacion catalogoMediosBonificacion;

	// Relacion usuario medio de bonificacion
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario;

	public void setIdMediosBonificacion(Long idMediosBonificacion) {
		this.idMediosBonificacion = idMediosBonificacion;
	}

	public Long getIdMediosBonificacion() {
		return idMediosBonificacion;
	}

	public String getAliasMedioBonificacion() {
		return aliasMedioBonificacion;
	}

	public void setAliasMedioBonificacion(String aliasMedioBonificacion) {
		this.aliasMedioBonificacion = aliasMedioBonificacion;
	}

	public void setCuentaMedioBonificacion(String cuentaMedioBonificacion) {
		this.cuentaMedioBonificacion = cuentaMedioBonificacion;
	}

	public String getCuentaMedioBonificacion() {
		return cuentaMedioBonificacion;
	}

	public void setCompaniaMedioBonificacion(String companiaMedioBonificacion) {
		this.companiaMedioBonificacion = companiaMedioBonificacion;
	}

	public String getCompaniaMedioBonificacion() {
		return companiaMedioBonificacion;
	}

	public String getVigenciaMedioBonificacion() {
		return vigenciaMedioBonificacion;
	}

	public void setVigenciaMedioBonificacion(String vigenciaMedioBonificacion) {
		this.vigenciaMedioBonificacion = vigenciaMedioBonificacion;
	}

	public String getIdCuentaMedioBonificacion() {
		return idCuentaMedioBonificacion;
	}

	public void setIdCuentaMedioBonificacion(String idCuentaMedioBonificacion) {
		this.idCuentaMedioBonificacion = idCuentaMedioBonificacion;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public void setCatalogoMediosBonificacion(CatalogoMediosBonificacion catalogoMediosBonificacion) {
		this.catalogoMediosBonificacion = catalogoMediosBonificacion;
	}

	public CatalogoMediosBonificacion getCatalogoMediosBonificacion() {
		return catalogoMediosBonificacion;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
