package com.bit.communication;

import com.bit.exception.CommunicationException;
import com.bit.model.dto.SMSData;
import com.bit.model.dto.SimpleResponse;

/**
 * Se definen las firmas de los metodos para el envio de SMSs
 * a los usuarios del sistema
 * @author juanosorioalvarez
 *
 */
public interface SMS {

	/**
	 * Envio de SMS, usualmente se envia el codigo de activacion dentro del body
	 * @param data, contiene el numero del destinatario y el cuerpo del mensaje
	 */
	SimpleResponse sendSMS(SMSData data) throws CommunicationException;
}
