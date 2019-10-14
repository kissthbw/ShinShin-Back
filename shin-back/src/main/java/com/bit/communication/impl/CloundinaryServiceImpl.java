package com.bit.communication.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bit.communication.CloundinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloundinaryServiceImpl implements CloundinaryService {

	private static final Logger log = LoggerFactory.getLogger(CloundinaryServiceImpl.class);
	
	private Cloudinary cloudinary;
	
	public CloundinaryServiceImpl() {
		cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "shingshing",
				  "api_key", "657472936977876",
				  "api_secret", "cZ8wZWzSvTqXdqBO7P1e62xnzVY")); 
	}
	
	@Override
	public String uploadImage(byte[] bytes, Map params) {
//		Map params = ObjectUtils.asMap(
//				   "public_id", "shingshing/more", 
//				   "overwrite", true
//				);
		
		try {
			Map uploadResult = cloudinary.uploader().upload(bytes, params);
			String url = (String) uploadResult.get("url");
			
			return url;
		} catch (IOException e) {
			log.error("Ocurrio un error al subir imagen", e);
		}
		return null;
	}

}
