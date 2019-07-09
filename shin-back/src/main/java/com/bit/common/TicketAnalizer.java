package com.bit.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketAnalizer {
	
	private static final String OXXO_FECHA_PATTERN = "(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](19|20)\\d\\d";
	private static final String OXXO_HORA_PATTERN = "(\\d\\d:\\d\\d)";
	
	//
	private static Map<String, String> tiendas = new HashMap<>();
	static {
		tiendas.put("OXXO","OXXO");
		tiendas.put("Oxx0","OXXO");
		tiendas.put("Oxxa","OXXO");
		tiendas.put("Oxx0","OXXO");
	}
	
	public static String analize(List<String> lineas) {
		
		for (String linea : lineas) {
			detectarTienda(linea);
			detectaFecha(linea);
			detectaHora(linea);
		}
		
		return "";
	}
	
	private static String detectarTienda( String linea ) {
//		Iterator it = tiendas.entrySet().iterator();
//		
//		while( it.hasNext() ) {
//			Map.Entry pair = (Map.Entry)it.next();
//	        System.out.println(pair.getKey() + " = " + pair.getValue());
//		}
		
		for (Map.Entry<String, String> entry : tiendas.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    
		    if( linea.contains(key) ) {
		    	System.out.println( "Es un OXXO" );
		    }
		}
		
		return "";
	}
	
	private static String detectaFecha(String linea) {
		Pattern p = Pattern.compile( OXXO_FECHA_PATTERN );
		Matcher m = p.matcher(linea);
		String valor = "";
		
		while (m.find()) {
			valor = m.group();
			System.out.println( "Fecha: " + valor );
		}
		
		return valor;
	}
	
	private static String detectaHora( String linea ) {
		Pattern p = Pattern.compile( OXXO_HORA_PATTERN );
		Matcher m = p.matcher(linea);
		String valor = "";
		
		while (m.find()) {
			valor = m.group();
			System.out.println( "Hora: " + valor );
		}
		
		return valor;
	}
}
