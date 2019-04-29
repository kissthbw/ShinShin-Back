package com.bit.communication.impl;

import com.bit.communication.SMS;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.SMSData;
import com.bit.model.dto.SimpleResponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSImpl implements SMS {

	//Mover a variables de ambiente o archivo de propiedades 
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	
	@Override
	public SimpleResponse sendSMS(SMSData data) throws CommunicationException {
		SimpleResponse rsp = new SimpleResponse();
		
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			
			
			Message message = Message.creator(
					new PhoneNumber( data.getToMobileNumber() ),//"+525550679875" 
					new PhoneNumber("+12512205528"),
					data.getBody()).create();
			
			System.out.println(message.getSid());
			rsp.setCode( message.getErrorCode() );
			rsp.setMessage( message.getSid() );
		}
		catch( Exception e ) {
			e.printStackTrace();
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}
		
		return rsp;
	}

}
