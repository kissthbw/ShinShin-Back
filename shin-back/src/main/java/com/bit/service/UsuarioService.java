package com.bit.service;

import java.math.BigDecimal;

import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;

public interface UsuarioService {

	ListItemsRSP getUsuarios();

	SimpleResponse registrarUsuarios(Usuario item);
	
	InformacionUsuarioRSP registrarUsuarioSocialMedia(Usuario item);

	SimpleResponse reenviarCodigoUsuario(Usuario item);
	
	SimpleResponse activarUsuarios(Usuario item);
	
	SimpleResponse eliminarUsuario(Usuario item);

	SimpleResponse actualizarUsuarios(Usuario item);

	Usuario findUserByUser(Usuario item);

	InformacionUsuarioRSP findUserByUserAndPassword(Usuario item);

	InformacionUsuarioRSP obtenerTotalBonificacion(Usuario item);
	
	InformacionUsuarioRSP obtenerMediosBonificacion(Usuario item);
	
	InformacionUsuarioRSP registrarTicketUsuario(Usuario item);
	
	ListItemsRSP obtienetHistoricosMediosBonificacionPorUsuario(Usuario item);
	
	ListItemsRSP obtieneTicketsPorUsuario(Usuario item);
	
	BigDecimal calculaCreditoTotal(Usuario user); 
	
	//Metodos relacionados con el dashboard del usuario
	//obtiene informacion de totales para sre mostrada
	InformacionUsuarioRSP obtieneInformacionGeneralUsuario(Usuario item);
}
