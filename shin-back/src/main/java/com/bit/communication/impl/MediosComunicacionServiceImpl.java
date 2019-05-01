package com.bit.communication.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.bit.communication.MediosComunicacionService;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.EMailDTO;
import com.bit.model.dto.SMSDTO;
import com.bit.model.dto.SimpleResponse;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MediosComunicacionServiceImpl implements MediosComunicacionService {

	// Mover a variables de ambiente o archivo de propiedades
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";

	@Override
	public SimpleResponse sendEmail(EMailDTO data) throws CommunicationException {

		SimpleResponse rsp = new SimpleResponse();

		// 1. Correo del emisor, debe ser de ls cuenta del cliente
		Email from = new Email("test@example.com");

		// 2. Correo del usuario que se registro
		Email to = new Email(data.getToAccount());

		// 3. Asunto del correo, definir un titulo constante
		String subject = data.getSubject();

		// 4. Definir el contenido del correo, el cual debe incluir el codigo de
		// verificacion
		Content content = new Content("text/plain", data.getBody());

		Mail mail = new Mail(from, subject, to, content);
		mail.setTemplateId("d-655f8fc807da4483a4d6b5669d6b1bd2");

//		Map<String, String> envs = System.getenv();
//		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		// El API KEY debe ser puesto en una variable de ambiente
		SendGrid sg = new SendGrid("");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			rsp.setCode(response.getStatusCode());
			rsp.setMessage(response.getBody());

			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}

		return rsp;
	}

	@Override
	public SimpleResponse sendSMS(SMSDTO data) throws CommunicationException {
		SimpleResponse rsp = new SimpleResponse();

		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			Message message = Message.creator(new PhoneNumber(data.getToMobileNumber()), // "+525550679875"
					new PhoneNumber("+12512205528"), data.getBody()).create();

			System.out.println(message.getSid());
			rsp.setCode(message.getErrorCode());
			rsp.setMessage(message.getSid());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}

		return rsp;
	}

}
