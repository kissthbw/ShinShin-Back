package com.bit.communication.impl;

import java.io.IOException;

import com.bit.communication.EMail;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.EMailData;
import com.bit.model.dto.SimpleResponse;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class EMailImpl implements EMail {

	@Override
	public SimpleResponse sendEmail(EMailData data) throws CommunicationException {
		
		SimpleResponse rsp = new SimpleResponse();
		
		//1. Correo del emisor, debe ser de ls cuenta del cliente
		Email from = new Email("test@example.com");
		
		//2. Correo del usuario que se registro
		Email to = new Email( data.getToAccount() );
		
		//3. Asunto del correo, definir un titulo constante
		String subject = data.getSubject();
		
		//4. Definir el contenido del correo, el cual debe incluir el codigo de verificacion
		Content content = new Content("text/plain", data.getBody());
		Mail mail = new Mail(from, subject, to, content);

//		Map<String, String> envs = System.getenv();
//		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		//El API KEY debe ser puesto en una variable de ambiente
		SendGrid sg = new SendGrid( "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" );
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			rsp.setCode( response.getStatusCode() );
			rsp.setMessage( response.getBody() );
			
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}
		
		return rsp;
	}

}
