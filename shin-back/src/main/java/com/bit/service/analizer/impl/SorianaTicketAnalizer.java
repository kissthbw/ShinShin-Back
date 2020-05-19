package com.bit.service.analizer.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.exception.TicketException;
import com.bit.model.dto.response.OCRTicketRSP;
import com.bit.service.analizer.Analizer;
import com.bit.service.analizer.TicketAnalizer;

@Service
public class SorianaTicketAnalizer implements TicketAnalizer {
	
	private static final Logger log = LoggerFactory.getLogger(SorianaTicketAnalizer.class);

	private static final String ID_TIENDA_CATALOGO_PATTERN = "SORIANA";
	
	//#####
	private static final String ID_SORIANA_FECHA_PATTERN = "ID_SORIANA_FECHA_PATTERN";
	private static final String ID_SORIANA_HORA_PATTERN =   "ID_SORIANA_HORA_PATTERN";
	private static final String ID_SUCURSAL =   "ID_SUCURSAL";
	private static final String ID_SERIAL_NUMBER =   "ID_SERIAL_NUMBER";
	
	private String SORIANA_FECHA_PATTERN = "(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](20)\\d\\d";
	private String SORIANA_HORA_PATTERN = "(\\d\\d:\\d\\d:\\d\\d)";
	private String SUCURSAL = "\\d{3}\\s\\d{3}\\s\\d+\\s\\d+";
	private String SERIAL_NUMBER = "[0-9]{5}";
	
	@Autowired
	private Analizer analizer;
	
	private static Map<String, String> FOOTERS = new HashMap<>();
	static {
		FOOTERS.put("TOTAL", "TOTAL");
		FOOTERS.put("TOTAI", "TOTAL");
	}
	
	@Override
	public OCRTicketRSP analize(List<String> lineas) throws TicketException{
		
		OCRTicketRSP rsp = new OCRTicketRSP();
		rsp.setTienda("SORIANA");
		rsp.setTieneCB(true);
		
		Map<String, String> patternMap = analizer.obtieneCatalogoPattern( ID_TIENDA_CATALOGO_PATTERN );
		if( !patternMap.isEmpty() ) {
			SORIANA_FECHA_PATTERN = patternMap.get( ID_SORIANA_FECHA_PATTERN ) != null ? patternMap.get( ID_SORIANA_FECHA_PATTERN ) : SORIANA_FECHA_PATTERN;
			SORIANA_HORA_PATTERN = patternMap.get( ID_SORIANA_HORA_PATTERN ) != null ? patternMap.get( ID_SORIANA_HORA_PATTERN ) : SORIANA_HORA_PATTERN;
			SUCURSAL = patternMap.get( ID_SUCURSAL ) != null ? patternMap.get( ID_SUCURSAL ) : SUCURSAL;
			SERIAL_NUMBER = patternMap.get( ID_SERIAL_NUMBER ) != null ? patternMap.get( ID_SERIAL_NUMBER ) : SERIAL_NUMBER;

		}
		
		String valor = "";
		List<Integer> posList = new ArrayList<Integer>();
		

		ListIterator<String> it = lineas.listIterator();
		it = lineas.listIterator();
		int pos = detectaPosicionTransaccion(it, SORIANA_FECHA_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, SORIANA_HORA_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, SUCURSAL);
		if (pos != -1) {
			posList.add( pos );
		}
		
		
		Collections.sort(posList);
		
		it = lineas.listIterator();
		depuraHeader(it, posList.get(0));
		
		//Para que se un ticket valido deben exisitir 
		//los elementos TDA, OP, TE, TR
		it = lineas.listIterator();
		valor = detectaHora(it);
		if ( !"".equalsIgnoreCase(valor) ) {
			valor = valor.replace("O", "0");
			rsp.setHora(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaFecha(it);
		if ( !"".equalsIgnoreCase(valor) ) {
			valor = valor.replace("O", "0");
			rsp.setFecha(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaTransaccion(it, SUCURSAL);
		if ( !"".equalsIgnoreCase(valor) ) {
			valor = valor.replace("O", "0");
			rsp.setTransaccion(valor);
		}
		
		validarTicket(rsp);
		
		rsp.setTransaccion( rsp.getTransaccion().replace(" ", "") );
		
		it = lineas.listIterator();
		depuraFooter(it);
		
		it = lineas.listIterator();
		depuraProductos(it);
		lineas.removeIf( item -> item == null || "".equals(item.trim()) );
		
		System.out.println(lineas);
		rsp.setLineas(lineas);
		
		return rsp;
	}

	private String detectaTransaccion( ListIterator<String> it, String pattern ) {
		//Identificar transaccion
		//TDA#2670 OPHO0000214TE 003 TR# 08103
		//TDA#2079 OP#00000131 TE# 023 TR# 05012
		//Obtener TR# hasta el final
		//Obtener TE# hasta el final
		//Obtener OP# hasta el final
		//Obtener TDA# hasta el final
		
		String valor = "";

		search: while (it.hasNext()) {
			String linea = it.next();

			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(linea);

			while (m.find()) {
				valor = m.group();
				linea = linea.replace(valor, "").trim();
				it.set(linea);
				break search;
			}
		}

		return valor;
	}
	
	private int detectaPosicionTransaccion( ListIterator<String> it, String pattern ) {
		//Identificar transaccion
		//TDA#2670 OPHO0000214TE 003 TR# 08103
		//TDA#2079 OP#00000131 TE# 023 TR# 05012
		//Obtener TR# hasta el final
		//Obtener TE# hasta el final
		//Obtener OP# hasta el final
		//Obtener TDA hasta el final
		int index = 0;
		int pos = -1;

		search: while (it.hasNext()) {
			String linea = it.next();
			index ++;

			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(linea);

			if( m.find() ) {
				pos = index;
				break search;
			}
			else {
				pos = -1;
			}
		}

		return pos;
	}
	
	private void detectaProductos( ListIterator<String> it ) {
		//Los productos vienen en formato:
		//[TEXTO] STEFANO AERO 7509546064697$
		
	}
	
	private String detectaFecha(ListIterator<String> it) {

		String valor = "";

		search: while (it.hasNext()) {
			String linea = it.next();

			Pattern p = Pattern.compile(SORIANA_FECHA_PATTERN);
			Matcher m = p.matcher(linea);

			while (m.find()) {
				valor = m.group();
				System.out.println("Fecha: " + valor);
				linea = linea.replace(valor, "").trim();
				it.set(linea);
				break search;
			}
		}

		return valor;
	}

	private String detectaHora(ListIterator<String> it) {
		String valor = "";

		search: while (it.hasNext()) {
			String linea = it.next();

			Pattern p = Pattern.compile(SORIANA_HORA_PATTERN);
			Matcher m = p.matcher(linea);

			while (m.find()) {
				valor = m.group();
				System.out.println("Fecha: " + valor);
				linea = linea.replace(valor, "").trim();
				it.set(linea);
				break search;
			}
		}

		return valor;
	}
	
	private void depuraHeader(ListIterator<String> it, int pos) {
		//Se deben eliminar las lineas hasta que se encuentra la seccion de transaccion
		//TDA#2670 OPHO0000214TE 003 TR# 08103
		
		int index = 0;
		
		search: while (it.hasNext()) {
			String linea = it.next();

			if (index < pos - 1) {
				index++;
				it.remove();
			}
			else {
				break search;
			}
		}
	}
	
	private void depuraFooter(ListIterator<String> it) {
		boolean borrar = false;
		while (it.hasNext()) {
			String linea = it.next();

			if (borrar) {
				it.remove();
				continue;
			}

			search: for (Map.Entry<String, String> entry : FOOTERS.entrySet()) {
				String key = entry.getKey();
				if (linea.contains(key)) {
					borrar = !borrar;
					it.remove();
					break search;
				}

			}

		}
	}
	
	private void depuraProductos(ListIterator<String> it) {
		//Elminar la parte del codigo de barras y precio de las lineas que contienen
		//los productos
		//Ejemplo: STEFANO AERO 7509546064697$
		//Se debe quitar 7509546064697$

		search:
		while (it.hasNext()) {
			String linea = it.next();
			
			if ( linea.trim().equals("") ) {
				it.remove();
				continue search;
			}
			
			linea = linea.replaceAll("\\$", "");

			Pattern p = Pattern.compile(SERIAL_NUMBER);
			Matcher m = p.matcher(linea);

			if ( m.find() ) {
				int index = m.start();
				linea = linea.substring(0, index).trim();
				it.set(linea);
				continue search;
			}
		}
	}
	
	private static void depuraSignos(ListIterator<String> it) {
		while( it.hasNext() ) {
			String linea = it.next();
			
			linea = linea.replaceAll("\\$", "");
			it.set(linea);
		}
	}
	
	private void validarTicket(OCRTicketRSP rsp) throws TicketException {
		if ( rsp.getHora() != null && rsp.getFecha() != null &&
				rsp.getTransaccion() != null
				) {
			System.out.println( "Ticket OK" );
			
		}
		else {
			System.out.println( "Ticket incompleto" );
			Throwable t = new Throwable("Identificadores de transaccion o fechas incompletas");
			throw new TicketException("Ticket con identificadores incompletos", t, 500);
		}
	}

}
