package com.bit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "ap_paterno")
	private String apPaterno;

	@Column(name = "ap_materno")
	private String apMaterno;

	@Column(name = "fecha_nac")
	@Temporal(TemporalType.DATE)
	private Date fechaNac;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "contrasenia")
	private String contrasenia;

	@Column(name = "calle")
	private String calle;

	@Column(name = "num_ext")
	private String numeroExt;

	@Column(name = "num_int")
	private String numeroInt;

	@Column(name = "colonia")
	private String colonia;

	@Column(name = "codigo_postal")
	private String codigoPostal;

	@Column(name = "del_mun")
	private String delMun;

	@Column(name = "estado")
	private String estado;

	@Column(name = "tel_local")
	private String telLocal;

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCalle() {
		return calle;
	}

	public void setNumeroExt(String numeroExt) {
		this.numeroExt = numeroExt;
	}

	public String getNumeroExt() {
		return numeroExt;
	}

	public void setNumeroInt(String numeroInt) {
		this.numeroInt = numeroInt;
	}

	public String getNumeroInt() {
		return numeroInt;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getColonia() {
		return colonia;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setDelMun(String delMun) {
		this.delMun = delMun;
	}

	public String getDelMun() {
		return delMun;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setTelLocal(String telLocal) {
		this.telLocal = telLocal;
	}

	public String getTelLocal() {
		return telLocal;
	}

}
