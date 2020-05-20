package com.bit.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.communication.MediosComunicacionService;
import com.bit.dao.ProblemaDAO;
import com.bit.exception.CommunicationException;
import com.bit.model.Problema;
import com.bit.model.dto.EMailDTO;
import com.bit.service.ProblemaService;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class ProblemaServiceImpl implements ProblemaService{
	
	private static final Logger log= LoggerFactory.getLogger(ProblemaServiceImpl.class);
	
	@Autowired
	private ProblemaDAO problemaDAO;
	
	@Autowired
	private MediosComunicacionService mediosComunicacionService;
		
	@Override
	@Transactional
	public String guardaProblemas(Problema problema) {
		
		EMailDTO data = new EMailDTO();
		data.setSubject( "Se reporto un problema" );
		data.setToAccount( "soporte@tradenial.com" );
//		data.setToAccount( "kissthbw@gmail.com" );
		
		Personalization personalization = new Personalization();

		
		personalization.addDynamicTemplateData("link", problema.getProblema());		
		data.setPersonalization( personalization );
		data.setTemplateId("d-b3ab8edcad864ffea5abd643df90375a");

		try {
			mediosComunicacionService.sendCustomEmail(data);
		} catch (CommunicationException e) {
			log.error("", e);
		}
		
		problemaDAO.save(problema);
		
		return "OK";
	}

}
