package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "fotografias_ticket")
public class FotografiasTicket {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_ticket")
	private Long idTicket;

	@Column(name = "url_foto_ticket")
	private String urlFotoTicket;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public String getUrlFotoTicket() {
		return urlFotoTicket;
	}

	public void setUrlFotoTicket(String urlFotoTicket) {
		this.urlFotoTicket = urlFotoTicket;
	}
}
