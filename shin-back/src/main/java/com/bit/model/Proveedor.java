package com.bit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity(name = "proveedor")
public class Proveedor {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_marca")
	private CatalogoMarca marca;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "proveedor_authority", joinColumns = { @JoinColumn(name = "proveedor_id") }, inverseJoinColumns = {
			@JoinColumn(name = "authority_id") })
	private Set<Authority> authorities = new HashSet<>();
	
	@Transient
	private String marcaImageURL;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CatalogoMarca getMarca() {
		return marca;
	}

	public void setMarca(CatalogoMarca marca) {
		this.marca = marca;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getMarcaImageURL() {
		return marcaImageURL;
	}

	public void setMarcaImageURL(String marcaImageURL) {
		this.marcaImageURL = marcaImageURL;
	}
}
