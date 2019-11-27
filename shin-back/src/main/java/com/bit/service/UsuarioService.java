package com.bit.service;

import java.math.BigDecimal;

import com.bit.model.Contacto;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.impl.UsuarioServiceImpl.Source;

public interface UsuarioService {

	ListItemsRSP getUsuarios();

	InformacionUsuarioRSP registrarUsuarios(Usuario item, Source source);
	
	InformacionUsuarioRSP registrarUsuarioSocialMedia(Usuario item);

	InformacionUsuarioRSP reenviarCodigoUsuario(Usuario item, Source source);
	
	SimpleResponse activarUsuarios(Usuario item, Source source);
	
	SimpleResponse eliminarUsuario(Usuario item);

	InformacionUsuarioRSP actualizarUsuarios(Usuario item);

	Usuario findUserByPK(Usuario item);
	
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
	
	SimpleResponse registraContacto(Contacto item);
	
	SimpleResponse solicitarRestaurarPassword(Usuario item);
	
	SimpleResponse restaurarPasswordLink(Usuario item);
	
	SimpleResponse restaurarPassword(Usuario item);
	
	InformacionUsuarioRSP activationPasswordLink(Usuario item);
}
