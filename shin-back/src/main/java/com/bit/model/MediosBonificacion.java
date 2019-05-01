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

	@Column(name = "cuenta_medio_bonificacion")
	private String cuentaMedioBonificacion;

	@Column(name = "compania_medio_bonificacion")
	private String companiaMedioBonificacion;

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
