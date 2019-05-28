package com.bit.communication.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bit.communication.PayPalCommunication;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.BonificacionDTO;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.request.PayPalPayoutRQT;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PayPalCommunicationImpl implements PayPalCommunication {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public SimpleResponse token() throws CommunicationException{
		HttpEntity<String> httpEntity;
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Basic " + "QVJLYWd6dFNJb0lHNmM0dWJhYjd6Q2xvME93NEpScWJXcTZMN1E0NnZqWmt1T2F2Mzh0S3VHUnh6TGtlYWdSbGJlNjRINUZiN2IxRkd5aHM6RUhVSjhUM2I1VzZpSzRVWW5FU1JkVV9zYlRrMnNFY2ZySGMzTjNkcmNJbWFra0dZeG1KajhXX1hCRk5vWExEd05Mc2NMOTNZcnlhQ05ST2w");
			httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
			httpEntity = new HttpEntity<String>("grant_type=client_credentials", httpHeaders) ;
			ResponseEntity<String> responseEntity = restTemplate.exchange("https://api.sandbox.paypal.com//v1/oauth2/token", HttpMethod.POST, httpEntity, String.class);
			System.out.println( responseEntity.getBody() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public SimpleResponse bonificacionPayPal(PayPalPayoutRQT rqt, String bearerAuth ) throws CommunicationException {
		HttpEntity<String> httpEntity;
		try {
			final Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put("charset", "utf-8");
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Bearer S" + bearerAuth);
			httpHeaders.setContentType( new MediaType("application","json", parameterMap));
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			
			httpEntity = new HttpEntity<String>(parseToJson(rqt), httpHeaders);
			ResponseEntity<String> responseEntity = restTemplate.exchange("https://api.sandbox.paypal.com/v1/payments/payouts", HttpMethod.POST, httpEntity, String.class);
			System.out.println( responseEntity.getBody() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public SimpleResponse bonificacionTiempoAire(BonificacionDTO data) throws CommunicationException {
		return null;
	}

	public String parseToJson(Object object) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new ObjectMapper().writeValueAsString(object));

		return stringBuilder.toString();
	}
	
	HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		final Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("charset", "utf-8");
		//httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setContentType( new MediaType("application","json", parameterMap));
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.add("Authorization", "Basic " + "cndzcHJheGlzcDpQcjR4MXMjdTVS");
		return httpHeaders;
	}
}
