package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.communication.FireBaseAdmin;
import com.bit.communication.MediosComunicacionService;
import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.dao.MediosBonificacionDAO;
import com.bit.exception.CommunicationException;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.EMailDTO;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.HistoricoMediosBonificacionService;
import com.bit.service.UsuarioService;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class HistoricoMediosBonificacionServiceImpl implements HistoricoMediosBonificacionService {
	
	private static final Logger log = LoggerFactory.getLogger(HistoricoMediosBonificacionServiceImpl.class);
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;
	
	@Autowired
	private MediosBonificacionDAO mediosBonificacionDAO;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FireBaseAdmin fireBaseAdmin;
	
	@Autowired
	private MediosComunicacionService mediosComunicacionService;

	@Override
	@Transactional
	public ListItemsRSP getHistoricosMediosBonificacion() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtiene una lista de bonificaciones");
		
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionDAO.getHistoricosMediosBonificacion();
		
		rsp.setHistoricoMediosBonificaciones(list);
		return rsp;
	}
	
	@Override
	@Transactional
	public ListItemsRSP getHistoricosMediosBonificacionPorUsuario(Usuario item) {
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obtiene una lista de bonificaciones del usuario {}", item.getIdUsuario());
		
		List<HistoricoMediosBonificacion> list = historicoMediosBonificacionDAO.getHistoricosMediosBonificacionPorUsuario(item, 200);
		
		rsp.setHistoricoMediosBonificaciones(list);
		return rsp;
	}

	@Override
	@Transactional
	public InformacionUsuarioRSP registrarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		
		log.info("Registrando historial de medios de bonificacion");
		
		InformacionUsuarioRSP rsp = new InformacionUsuarioRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		//Obtener el credito disponible
		Usuario user = new Usuario();
		user.setIdUsuario( item.getUsuario().getIdUsuario() );
		Double credito = usuarioService.calculaCreditoTotal(user).doubleValue();
		
		if ( credito < item.getCantidadBonificacion() ) {
			rsp.setMessage("Fondos insuficientes");
			rsp.setCode(202);
		}
		else {
			item = historicoMediosBonificacionDAO.save(item);
			rsp.setId(item.getIdHistoricoMediosBonificacion());
			
			MediosBonificacion mb = mediosBonificacionDAO.findByPK( item.getMediosBonificacion().getIdMediosBonificacion() );
			
			//Integrar con Proveedor de recargas
			
			//Enviar notificación
			//Obtener el device token del usuario
//			String deviceToken = usuarioService.obtieneDeviceTokenPorUsuario( user );
			Usuario u = usuarioService.findUserByPK(user);
			
			if( null != u ) {
				if( null != u.getDeviceToken() && !"".equals( u.getDeviceToken() ) ) {
					log.info( "Enviando push notification a usuario: {}", u.getDeviceToken() );
					fireBaseAdmin.sendPushNotificationToDevice( u.getDeviceToken(),
							"Se esta procesando tu solicitud");
				}
			}
			
			
			log.info("Enviando correo de notificacion (Solicitud de returo) a administrador");
			EMailDTO data = new EMailDTO();
			data.setSubject( "Solicitud de retiro" );
			data.setToAccount( "roberto.guadarrama@tradenial.com" );
			
			Personalization personalization = new Personalization();

			
			personalization.addDynamicTemplateData("usuario", u.getUsuario());
			personalization.addDynamicTemplateData("monto", item.getCantidadBonificacion());
			personalization.addDynamicTemplateData("tipo", mb.getCatalogoMediosBonificacion().getNombreMedioBonificacion());
			personalization.addDynamicTemplateData("cuenta", mb.getCuentaMedioBonificacion());
			data.setPersonalization( personalization );
			
			StringBuilder text = new StringBuilder();
			text.append( "EL usuario" );
			
			data.setBody( "" );
			try {
				mediosComunicacionService.sendWithdrawalRequestEmail(data);
			} catch (CommunicationException e) {
				log.error("", e);
			}
		}
		
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarHistoricosMediosBonificacion(HistoricoMediosBonificacion item) {
		
		log.info("Actualizando historial de medios de bonificacion");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		historicoMediosBonificacionDAO.findByPK(item.getIdHistoricoMediosBonificacion());
		
		item = historicoMediosBonificacionDAO.update(item);
		rsp.setId(item.getIdHistoricoMediosBonificacion());
		return rsp;
	}

}
