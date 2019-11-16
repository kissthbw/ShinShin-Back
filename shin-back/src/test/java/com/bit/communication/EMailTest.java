package com.bit.communication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.config.WebConfig;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.EMailDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class EMailTest {
	
	@Autowired
	private MediosComunicacionService mediosComunicacionService;
	
	@Test
	public void sendEMail() {
		EMailDTO data = new EMailDTO();
		data.setToAccount("kissthbw@gmail.com");
		data.setSubject("Restablecer password");
		String link = "www.shingshing.com/restaurar/a109a2817b8306bc256b01dcf125eca8d7aab86530da0b4e1f8948dd797df289";
		data.setBody(link);
		
		try {
			mediosComunicacionService.sendEmail(data);
		} catch (CommunicationException e) {
			e.printStackTrace();
		}
	}
}
