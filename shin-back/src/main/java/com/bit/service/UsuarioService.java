package com.bit.service;

import java.util.List;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;

public interface UsuarioService {

	List<Usuario> getUsuarios();

	SimpleResponse registrarUsuarios(Usuario item);

	SimpleResponse activarUsuarios(Usuario item);

	SimpleResponse actualizarUsuarios(Usuario item);

	Usuario findUserByUser(Usuario item);

	Usuario findUserByUserAndPassword(Usuario item);

	InformacionUsuarioRSP obtenerTotalBonificacion(Usuario item);
	
	InformacionUsuarioRSP obtenerMediosBonificacion(Usuario item);

}
