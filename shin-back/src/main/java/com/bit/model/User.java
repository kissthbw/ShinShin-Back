package com.bit.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -7983998111737199941L;

	private long idUsuario;
	private String username;
	private String password;

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
