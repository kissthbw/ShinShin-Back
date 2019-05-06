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

import com.bit.communication.BonificacionService;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.BonificacionDTO;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BonificacionServiceImpl implements BonificacionService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public SimpleResponse bonificacionPayPal(BonificacionDTO data) throws CommunicationException {
//		ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI, person, HttpStatus.class);
//		response.getBody();
		Test t = new Test();
		t.setEdad(30);
		HttpEntity<String> httpEntity;
		try {
//			httpEntity = new HttpEntity<String>(parseToJson(t), getHeaders());
			httpEntity = new HttpEntity<String>("{\"rqt\": {\"edad\":30,\"fecha\":\"2019-04-15\"}}", getHeaders());
//			ResponseEntity<String> responseEntity = restTemplate.exchange("http://169.53.251.4:8080/fdeeback/catalog/irn", HttpMethod.POST, httpEntity, String.class);
			ResponseEntity<String> responseEntity = restTemplate.exchange("https://www.proinversion.mx:2180/ImpulsaT/Oportunidades/ConsultarRendimientoNeto", HttpMethod.POST, httpEntity, String.class);
			System.out.println( responseEntity.getBody() );
//			IRNResponseDto response = parseToObject(responseEntity.getBody(),
//					IRNResponseDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
