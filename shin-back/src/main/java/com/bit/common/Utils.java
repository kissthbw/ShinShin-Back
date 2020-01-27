package com.bit.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bit.model.CatalogoDiccionarioTiendas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;

public class Utils {
	
	private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	 
	
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
	
	public static Map<String, String> obtieneCatalogo(){
		return Collections.unmodifiableMap( DICCIONARIO );
	}
	
	public static boolean isEmail( String email ) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		
		Matcher m = pattern.matcher(email);
		return m.find();
	}
	
	public static Period calcularEdad( Calendar fechaNac ) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(fechaNac.get( Calendar.YEAR ), 
				fechaNac.get( Calendar.MONTH ), 
				fechaNac.get( Calendar.DAY_OF_MONTH ));
		 
		Period p = Period.between(birthday, today);
		 
		return p;
	}
	
	//dd/mm/anio
	public static String formatFecha(Date fecha, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fecha);
	}
	
	public static void main (String[] args) {
		
		String s = "5534714616";
		System.out.printf("%s es email valido?: %s \n", s, Utils.isEmail(s));
		
//		System.out.println("codigo de verificacion: " + Utils.generaCodigoVerficacion());
	}

	
}
