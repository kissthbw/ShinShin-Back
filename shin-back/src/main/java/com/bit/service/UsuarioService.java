package com.bit.service;

import java.util.List;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;

public interface UsuarioService {

	List<Usuario> getUsuarios();

	SimpleResponse registrarUsuarios(Usuario item);

	SimpleResponse activarUsuarios(Usuario item);

	SimpleResponse actualizarUsuarios(Usuario item);

}
