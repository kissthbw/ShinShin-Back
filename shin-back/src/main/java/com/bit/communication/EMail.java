package com.bit.communication;

import com.bit.exception.CommunicationException;
import com.bit.model.dto.EMailData;
import com.bit.model.dto.SimpleResponse;

/**
 * Se definen las firmas de los metodos encargados del envio de emails
 * @author juanosorioalvarez
 *
 */
public interface EMail {
	
	/**
	 * Metodo encargado de realizar el envio de email a un destinatario especifico
	 * @param data, contiene la cuenta del destinatario, el asuno y el cuerpo del mensaje
	 * @return com.sendgrid.Response con el estatus del envio del email
	 */
	SimpleResponse sendEmail( EMailData data ) throws CommunicationException;
}
