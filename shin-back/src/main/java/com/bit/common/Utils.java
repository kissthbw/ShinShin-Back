package com.bit.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.bit.model.CatalogoDiccionarioTiendas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	private static final Map<String, String> DICCIONARIO;
	static{
		DICCIONARIO = new HashMap<String, String>();
	}
	
	public static String generaCodigoVerficacion() {
		Random rand = new Random();
		String id = String.format("%04d", rand.nextInt(9999));
		
		return id;
	}
	
	public static String generaHash(String originalString) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		
		return bytesToHex(encodedhash);
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public static String objectToJSON(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(o);
	}
	
	public static void cargaCatalogoDocumentosFilenet( List<CatalogoDiccionarioTiendas> list ){
		for( CatalogoDiccionarioTiendas item : list ){
			DICCIONARIO.put( item.getClaveDiccionario(), item.getValorDiccionario());
		}
		
	}
	
	public static Map<String, String> obtieneCatalogoFilenet(){
		return Collections.unmodifiableMap( DICCIONARIO );
	}
	
	public static void main (String[] args) {
		
		
		System.out.println("codigo de verificacion: " + Utils.generaCodigoVerficacion());
	}

}
