package com.bit.communication;

import com.bit.exception.CommunicationException;
import com.bit.model.dto.EMailDTO;
import com.bit.model.dto.SMSDTO;
import com.bit.model.dto.SimpleResponse;

/**
 * Se definen las firmas de los metodos encargados del envio de emails y sms
 * @author juanosorioalvarez
 *
 */
public interface MediosComunicacionService {
	
	/**
	 * Metodo encargado de realizar el envio de email a un destinatario especifico
	 * @param data, contiene la cuenta del destinatario, el asuno y el cuerpo del mensaje
	 * @return com.sendgrid.Response con el estatus del envio del email
	 */
	SimpleResponse sendEmail( EMailDTO data ) throws CommunicationException;
	
	/**
	 * Envio de SMS, usualmente se envia el codigo de activacion dentro del body
	 * @param data, contiene el numero del destinatario y el cuerpo del mensaje
	 */
	SimpleResponse sendSMS(SMSDTO data) throws CommunicationException;
}
