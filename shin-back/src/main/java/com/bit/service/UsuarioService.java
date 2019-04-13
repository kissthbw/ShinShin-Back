package com.bit.service;

import java.util.List;

import com.bit.model.Usuario;

public interface UsuarioService {
	
	List<Usuario> getUsuarios();

	void guardarUsuarios(Usuario item);
	
	void actualizarUsuarios(Usuario item);

}
