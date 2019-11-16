package com.bit.communication;

import java.io.IOException;
import java.util.Map;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

public class ExampleSendGrid {
	
	public static Mail buildDynamicTemplate() throws IOException {
	    Mail mail = new Mail();

	    Email fromEmail = new Email();
	    fromEmail.setName("Example User");
	    fromEmail.setEmail("test@example.com");
	    mail.setFrom(fromEmail);

	    mail.setTemplateId("");

	    Personalization personalization = new Personalization();
//	    personalization.addSubstitution("Sender_Name", "Example User");
//	    personalization.addSubstitution("Sender_Address", "Denver");
	    personalization.addTo(new Email("test@example.com"));
	    mail.addPersonalization(personalization);
	    
	    return mail;
	  }
	
	public static void main(String[] args) throws IOException {
		Email from = new Email("kissthbw@gmail.com");
		Personalization personalization = new Personalization();
		
		
		String subject = "Sending a template";
		Email to = new Email("kissthbw@hotmail.com");
		Content content = new Content("text/plain", "Sending a template");
		
		Mail mail = new Mail(from, subject, to, content);
		personalization.addTo(to);
		mail.setTemplateId("");
		mail.addPersonalization(personalization);

		Map<String, String> envs = System.getenv();
//		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		SendGrid sg = new SendGrid( "" );
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
}