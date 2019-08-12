package com.bit.common;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.bit.exception.TicketException;
import com.bit.model.dto.response.OCRTicketRSP;

public class Analizer {
	
	
	
	//
	private static Map<String, String> tiendas = new HashMap<>();
	static {
		tiendas.put("OXXO","OXXO");
		tiendas.put("Oxx0","OXXO");
		tiendas.put("Oxxa","OXXO");
		tiendas.put("Oxx0","OXXO");
		tiendas.put("GxD","OXXO");
		tiendas.put("Oxxo","OXXO");
		tiendas.put("Oyxo","OXXO");
		tiendas.put("OvxO","OXXO");
		tiendas.put("OwxO","OXXO");
		tiendas.put("Gxxo","OXXO");
		tiendas.put("xxo","OXXO");
		tiendas.put("Walmart","WALMART");
		tiendas.put("Wal mart","WALMART");
		tiendas.put("Soriona","SORIANA");
		tiendas.put("Soriana","SORIANA");
		tiendas.put("7 ELEVEN","7ELEVEN");
		tiendas.put("7-Eleven","7ELEVEN");
		
	}
	
	public static OCRTicketRSP analize(List<String> lineas, boolean fake) throws TicketException {
		
		OCRTicketRSP rsp = new OCRTicketRSP();
		
		if (fake) {
			ListIterator<String> it = lineas.listIterator();
			depuraSignos(it);
			
			rsp.setLineas(lineas);
			
			
			return rsp;
		}
		
		String valor = "";
		
		ListIterator<String> it = lineas.listIterator();
		valor = detectarTienda(it);
		
		if ( null != valor ) {
			TicketAnalizer analizer = TicketAnalizerFactory.getAnalizer(valor.toUpperCase());
			
			if( null != analizer ) {
				rsp = analizer.analize(lineas);
			}
			else {
				rsp.setCode(500);
				rsp.setMessage("Formato de ticket no reconocido");
			}
			
		}
		
		return rsp;
	}
	
	
	private static String detectarTienda( ListIterator<String> it ) {
		String valor = "";
		
		search:
		while( it.hasNext() ) {
			String linea = it.next();
			
			for (Map.Entry<String, String> entry : tiendas.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    
			    if( linea.toUpperCase().contains(key.toUpperCase()) ) {
			    	valor = value;
			    	System.out.println( "Es un " + valor );
			    	it.remove();
			    	break search;
			    }
			}
		}
		
		
		
		return valor;
	}
	
	private static void depuraSignos(ListIterator<String> it) {
		while( it.hasNext() ) {
			String linea = it.next();
			
			linea = linea.replaceAll("\\$", "");
			it.set(linea);
		}
	}
}
