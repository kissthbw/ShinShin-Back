package com.bit.communication.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.communication.MediosComunicacionService;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.EMailDTO;
import com.bit.model.dto.SMSDTO;
import com.bit.model.dto.SimpleResponse;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MediosComunicacionServiceImpl implements MediosComunicacionService {

	// Mover a variables de ambiente o archivo de propiedades
	public static final String ACCOUNT_SID = "AC021b53d8f2e2a1ba77deee1627bfad27";
	public static final String AUTH_TOKEN = "7760ead8994b3a1890012fb16b25f066";

	private static final Logger log= LoggerFactory.getLogger(MediosComunicacionServiceImpl.class);
	
	@Autowired
	private MediosComunicacionService mediosComunicacionService;
	
	@Override
	public SimpleResponse sendEmail(EMailDTO data) throws CommunicationException {

		SimpleResponse rsp = new SimpleResponse();
		Personalization personalization = new Personalization();
		
		log.info( "Enviando EMail a: {}", data.getToAccount() );
			
		// 1. Correo del emisor, debe ser de la cuenta del cliente
		Email from = new Email("contacto@tradenial.com");

		// 2. Correo del usuario que se registro
		Email to = new Email();
		to.setName( "Shing Shing" );
		to.setEmail( data.getToAccount() );
		personalization.addTo(to);

		// 3. Asunto del correo, definir un titulo constante
		String subject = data.getSubject();

		// 4. Definir el contenido del correo, el cual debe incluir el codigo de
		// verificacion
//		Content content = new Content("text/html", "www.espinof.com");
		String link = data.getBody();
		System.out.println( link );
		
//		personalization.addDynamicTemplateData("link", link);
		personalization.addDynamicTemplateData("link", data.getBody());
//		Mail mail = new Mail(from, subject, to, content);
		Mail mail = new Mail();
		mail.setFrom(from);
		mail.setSubject(subject);
		
		mail.setTemplateId("d-fa971b2ca8954a01b6a5712bd9a123e2");
		mail.addPersonalization(personalization);

//		Map<String, String> envs = System.getenv();
//		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		// El API KEY debe ser puesto en una variable de ambiente
		SendGrid sg = new SendGrid("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");
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
			log.error( "", e );
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}

		return rsp;
	}

	@Override
	public SimpleResponse sendSMS(SMSDTO data) throws CommunicationException {
		SimpleResponse rsp = new SimpleResponse();

		log.info( "Enviando SMS a: {}", data.getToMobileNumber() );
		
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			Message message = Message.creator(new PhoneNumber(data.getToMobileNumber()), // "+525550679875"
					new PhoneNumber("+12512205528"), data.getBody()).create();

			System.out.println(message.getSid());
			rsp.setCode(message.getErrorCode());
			rsp.setMessage(message.getSid());
		} catch (Exception e) {
			log.error( "", e );
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}

		return rsp;
	}

	@Override
	public SimpleResponse sendContactEmail(EMailDTO data) throws CommunicationException {

		log.info( "Enviando EMail de contacto" );

		SimpleResponse rsp = new SimpleResponse();
		Personalization personalization = new Personalization();
			
		// 1. Correo del emisor, debe ser de la cuenta del cliente
		Email from = new Email("contacto@tradenial.com");

		// 2. Correo del usuario que se registro
		Email to = new Email();
		to.setName( "Shing Shing" );
		to.setEmail( "contacto@tradenial.com" );
		personalization.addTo(to);

		// 3. Asunto del correo, definir un titulo constante
		String subject = data.getSubject();

		// 4. Definir el contenido del correo, el cual debe incluir el codigo de
		// verificacion
//		Content content = new Content("text/html", "www.espinof.com");
		String link = data.getBody();
		System.out.println( link );
		
//		personalization.addDynamicTemplateData("link", link);
		personalization.addDynamicTemplateData("link", data.getBody());
//		Mail mail = new Mail(from, subject, to, content);
		Mail mail = new Mail();
		mail.setFrom(from);
		mail.setSubject(subject);
		
		mail.setTemplateId("d-742ba7f42e044ec69d629bfb7dcd8e09");
		mail.addPersonalization(personalization);

//		Map<String, String> envs = System.getenv();
//		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		// El API KEY debe ser puesto en una variable de ambiente
		SendGrid sg = new SendGrid("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");
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
			log.error( "", e );
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}

		return rsp;
	
	}

	@Override
	public SimpleResponse sendWithdrawalRequestEmail(EMailDTO data) throws CommunicationException {

		log.info( "Enviando EMail con solicitud de retiro" );

		SimpleResponse rsp = new SimpleResponse();
//		Personalization personalization = new Personalization();
			
		// 1. Correo del emisor, debe ser de la cuenta del cliente
		Email from = new Email("contacto@tradenial.com");

		// 2. Correo del usuario que se registro
		Email to = new Email();
		to.setName( "Shing Shing" );
		to.setEmail( data.getToAccount() );
		data.getPersonalization().addTo(to);

		// 3. Asunto del correo, definir un titulo constante
		String subject = data.getSubject();

		// 4. Definir el contenido del correo
		Mail mail = new Mail();
		mail.setFrom(from);
		mail.setSubject(subject);
		
		mail.setTemplateId("d-3dab48a2b7c34746aa27b29b7be99682");
		mail.addPersonalization(data.getPersonalization());

//		Map<String, String> envs = System.getenv();
//		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		// El API KEY debe ser puesto en una variable de ambiente
		SendGrid sg = new SendGrid("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");
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
			log.error( "", e );
			throw new CommunicationException("Error en el envio de email", e.getCause(), -1);
		}

		return rsp;
	
	}

}
