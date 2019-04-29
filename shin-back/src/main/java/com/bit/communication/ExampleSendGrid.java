package com.bit.communication;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;
import java.io.IOException;
import java.util.Map;

public class ExampleSendGrid {
	public static void main(String[] args) throws IOException {
		Email from = new Email("test@example.com");
		String subject = "Sending with SendGrid is Fun";
		Email to = new Email("kissthbw@gmail.com");
		Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
		Mail mail = new Mail(from, subject, to, content);

		Map<String, String> envs = System.getenv();
//		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		SendGrid sg = new SendGrid( "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" );
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