package com.bit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "problema")
public class Problema {
	@Id
	@Column(name = "id_problema")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProblema;

	@Column(name = "problema")
	private String problema;
	
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema=problema;
	}
}
