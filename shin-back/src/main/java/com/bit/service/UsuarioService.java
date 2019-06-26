package com.bit.service;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;

public interface UsuarioService {

	ListItemsRSP getUsuarios();

	SimpleResponse registrarUsuarios(Usuario item);

	SimpleResponse activarUsuarios(Usuario item);

	SimpleResponse actualizarUsuarios(Usuario item);

	Usuario findUserByUser(Usuario item);

	InformacionUsuarioRSP findUserByUserAndPassword(Usuario item);

	InformacionUsuarioRSP obtenerTotalBonificacion(Usuario item);
	
	InformacionUsuarioRSP obtenerMediosBonificacion(Usuario item);
	
	SimpleResponse registrarTicketUsuario(Usuario item);
	
	ListItemsRSP obtienetHistoricosMediosBonificacionPorUsuario(Usuario item);
	
	ListItemsRSP obtieneTicketsPorUsuario(Usuario item);

}
