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
import com.bit.model.dto.response.ChedrauiTicketRSP;
import com.bit.model.dto.response.HEBTicketRSP;
import com.bit.model.dto.response.OCRTicketRSP;
import com.bit.service.analizer.Analizer;
import com.bit.service.analizer.TicketAnalizer;

@Service
public class HEBTicketAnalizer implements TicketAnalizer {

	private static final Logger log = LoggerFactory.getLogger(HEBTicketAnalizer.class);
	
	private static final String ID_TIENDA_CATALOGO_PATTERN = "H-E-B";
	
	private static final String ID_HEB_FECHA_PATTERN = "ID_HEB_FECHA_PATTERN";
	private static final String ID_HEB_HORA_PATTERN = "ID_HEB_HORA_PATTERN";
	private static final String ID_HEB_TRA_PATTERN = "ID_HEB__TRA_PATTERN";
	private static final String ID_HEB_FOLIO_PATTERN =   "ID_HEB_FOLIO_PATTERN";
	
	private String HEB_FECHA_PATTERN = "(0[1-9]|1[012])[-.](0[1-9]|[12][0-9]|3[01])[-.][0-9]{2}";
	private String HEB_HORA_PATTERN = "[0-9]{1,2}:[0-9]{2}[A|P]";
	private String HEB_TRA_PATTERN = "^[0-9]+\\b\\s";
	private String HEB_FOLIO_PATTERN =   "[0-9]+/[0-9]+/[0-9]+\\b";
	
	@Autowired
	private Analizer analizer;
	
	private static Map<String, String> HEB_FOOTERS = new HashMap<>();
	static {
		HEB_FOOTERS.put("TOTAL", "TOTAL");
		HEB_FOOTERS.put("TOTAI", "TOTAL");
	}
	
	@Override
	public OCRTicketRSP analize(List<String> lineas) throws TicketException{
		
		HEBTicketRSP rsp = new HEBTicketRSP();
		rsp.setTienda("HEB");
		rsp.setTieneCB(true);
		
		Map<String, String> patternMap = analizer.obtieneCatalogoPattern( ID_TIENDA_CATALOGO_PATTERN );
		if( !patternMap.isEmpty() ) {
			HEB_TRA_PATTERN = patternMap.get( ID_HEB_TRA_PATTERN ) != null ? patternMap.get( ID_HEB_TRA_PATTERN ) : HEB_TRA_PATTERN;
			HEB_FOLIO_PATTERN = patternMap.get( ID_HEB_FOLIO_PATTERN ) != null ? patternMap.get( ID_HEB_FOLIO_PATTERN ) : HEB_FOLIO_PATTERN;
			HEB_FECHA_PATTERN = patternMap.get( ID_HEB_FECHA_PATTERN ) != null ? patternMap.get( ID_HEB_FECHA_PATTERN ) : HEB_FECHA_PATTERN;
			HEB_HORA_PATTERN = patternMap.get( ID_HEB_HORA_PATTERN ) != null ? patternMap.get( ID_HEB_HORA_PATTERN ) : HEB_HORA_PATTERN;
		}
		
		ChedrauiTicketRSP tmp = new ChedrauiTicketRSP();
		String valor = "";
		List<Integer> posList = new ArrayList<Integer>();
		
		//1. Caso ideal buscar linea completa
		//Hacer la extracción de derecha a izquierda
		ListIterator<String> it = lineas.listIterator();
		
		it = lineas.listIterator();
		int pos = detectaPosicionTransaccion(it, HEB_TRA_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, HEB_FOLIO_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		Collections.sort(posList);
		
		it = lineas.listIterator();
		depuraFooterIdentificadores(it, posList.get(0));
		
		//Para que se un ticket valido deben exisitir 
		//los elementos SUC, TER, TRA, FOLIO
		it = lineas.listIterator();
		valor = detectaTransaccion(it, HEB_TRA_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			tmp.setTra(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaTransaccion(it, HEB_FOLIO_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			tmp.setFolio(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaFecha(it);
		tmp.setFecha(valor);
		rsp.setFecha(valor);
		
		it = lineas.listIterator();
		valor = detectaHora(it);
		tmp.setHora(valor);
		rsp.setHora(valor);
		
		tmp.validarTicket();
		rsp.setTransaccion( tmp.getSuc() + tmp.getTer() + tmp.getTra() + tmp.getFolio() );
		rsp.setTransaccion( rsp.getTransaccion().replace(" ", "") );
		
//		it = lineas.listIterator();
//		depuraFooter(it);
		
		it = lineas.listIterator();
		depuraProductos(it);
		
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

			Pattern p = Pattern.compile(HEB_FECHA_PATTERN);
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

			Pattern p = Pattern.compile(HEB_HORA_PATTERN);
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
	
	private void depuraFooterIdentificadores(ListIterator<String> it, int pos) {
		int index = 0;
		
		search: while (it.hasNext()) {
			String linea = it.next();
			if (index > pos) {
				it.remove();
			}
			index++;
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

			search: for (Map.Entry<String, String> entry : HEB_FOOTERS.entrySet()) {
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
			it.set(linea);
//			Pattern p = Pattern.compile(SERIAL_NUMBER);
//			Matcher m = p.matcher(linea);
//
//			if ( m.find() ) {
//				int index = m.start();
//				linea = linea.substring(0, index).trim();
//				it.set(linea);
//				continue search;
//			}
		}
	}
	
	private static void depuraSignos(ListIterator<String> it) {
		while( it.hasNext() ) {
			String linea = it.next();
			
			linea = linea.replaceAll("\\$", "");
			it.set(linea);
		}
	}
	
//	private void validarTicket(ChedrauiTicketRSP rsp) throws TicketException {
//		if ( rsp.getTda() != null && rsp.getOp() != null &&
//				rsp.getTe() != null && rsp.getTr() != null
//				) {
//			System.out.println( "Ticket OK" );
//			
//		}
//		else {
//			System.out.println( "Ticket incompleto" );
//			Throwable t = new Throwable("Identificadores de transaccion o fechas incompletas");
//			throw new TicketException("Ticket con identificadores incompletos", t, 500);
//		}
//	}

}
