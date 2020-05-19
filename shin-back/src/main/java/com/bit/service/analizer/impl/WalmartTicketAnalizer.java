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
import com.bit.model.dto.response.WalmartTicketRSP;
import com.bit.service.analizer.Analizer;
import com.bit.service.analizer.TicketAnalizer;

@Service
public class WalmartTicketAnalizer implements TicketAnalizer {

	private static final Logger log = LoggerFactory.getLogger(WalmartTicketAnalizer.class);
	
	private static final String ID_TIENDA_CATALOGO_PATTERN = "WALMART";
	
	//CP. #####
	private static final String ID_WALMART_CP_TIENDA = "ID_WALMART_CP_TIENDA";
	private static final String ID_TDA_PATTERN = "ID_TDA_PATTERN";
	private static final String ID_OP_PATTERN =   "ID_OP_PATTERN";
	private static final String ID_TE_PATTERN =   "ID_TE_PATTERN";
	private static final String ID_TR_PATTERN =   "ID_TR_PATTERN";
	private static final String ID_WALMART_FECHA_PATTERN = "ID_WALMART_FECHA_PATTERN";
	private static final String ID_WALMART_HORA_PATTERN = "ID_WALMART_HORA_PATTERN";
	
	private String CP_TIENDA_PATTERN = "(CP.\\s?[0-9]+|C.P.\\s?[0-9]+)";
	private String TDA_PATTERN = "TDA[\\#|H|M|N][0-9]+\\b";
	private String OP_PATTERN =   "[O|0]P[\\#|H|M|N][A-Z0-9]+\\b";
	private String TE_PATTERN =   "TE[\\#|H|M|N]\\s[0-9]+\\b";
	private String TR_PATTERN =   "TR[\\#|H|M|N|\\s]\\s[0-9]+\\b";
	private String WALMART_FECHA_PATTERN = "(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](19|20)";
	private String WALMART_HORA_PATTERN = "(\\d\\d:\\d\\d)";
	private String SERIAL_NUMBER = "[0-9]{5}";
	
	@Autowired
	private Analizer analizer;
	
	private static Map<String, String> WALMART_FOOTERS = new HashMap<>();
	static {
		WALMART_FOOTERS.put("TOTAL", "TOTAL");
		WALMART_FOOTERS.put("TOTAI", "TOTAL");
	}
	
	@Override
	public OCRTicketRSP analize(List<String> lineas) throws TicketException{
		
		WalmartTicketRSP rsp = new WalmartTicketRSP();
		rsp.setTienda("WALMART");
		rsp.setTieneCB(true);
		
		Map<String, String> patternMap = analizer.obtieneCatalogoPattern( ID_TIENDA_CATALOGO_PATTERN );
		if( !patternMap.isEmpty() ) {
			CP_TIENDA_PATTERN = patternMap.get( ID_WALMART_CP_TIENDA ) != null ? patternMap.get( ID_WALMART_CP_TIENDA ) : CP_TIENDA_PATTERN;
			
			TDA_PATTERN = patternMap.get( ID_TDA_PATTERN ) != null ? patternMap.get( ID_TDA_PATTERN ) : TDA_PATTERN;
			OP_PATTERN = patternMap.get( ID_OP_PATTERN ) != null ? patternMap.get( ID_OP_PATTERN ) : OP_PATTERN;
			TE_PATTERN = patternMap.get( ID_TE_PATTERN ) != null ? patternMap.get( ID_TE_PATTERN ) : TE_PATTERN;
			TR_PATTERN = patternMap.get( ID_TR_PATTERN ) != null ? patternMap.get( ID_TR_PATTERN ) : TR_PATTERN;
			WALMART_FECHA_PATTERN = patternMap.get( ID_WALMART_FECHA_PATTERN ) != null ? patternMap.get( ID_WALMART_FECHA_PATTERN ) : WALMART_FECHA_PATTERN;
			WALMART_HORA_PATTERN = patternMap.get( ID_WALMART_HORA_PATTERN ) != null ? patternMap.get( ID_WALMART_HORA_PATTERN ) : WALMART_HORA_PATTERN;
		}
		
		
		String valor = "";
		List<Integer> posList = new ArrayList<Integer>();
		
		//1. Caso ideal buscar linea completa
		//Hacer la extracción de derecha a izquierda
		ListIterator<String> it = lineas.listIterator();
		it = lineas.listIterator();
		int pos = detectaPosicionTransaccion(it, TDA_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, OP_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, TE_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, TR_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		valor = detectaPattern(it, CP_TIENDA_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			rsp.setCpTienda(valor);
		}
		
		Collections.sort(posList);
		
		it = lineas.listIterator();
		depuraHeader(it, posList.get(0));
		
		//Para que se un ticket valido deben exisitir 
		//los elementos TDA, OP, TE, TR
		it = lineas.listIterator();
		valor = detectaTransaccion(it, TDA_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			//TDA#2670
			//Sustiruir la 4 posicion en caso de que no sea #
			//Existe el caso en que la parte numerica los 0 sea reconocidos como O
			//deben de pasarse a 0
			valor = valor.replaceAll("[|H|M|N]", "\\#");
			valor = valor.replace("O", "0");
			rsp.setTda(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaTransaccion(it, OP_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			valor = valor.replaceAll("[|H|M|N]", "\\#");
			valor = "OP" + valor.substring(2, valor.length()).replace("O", "0");
			rsp.setOp(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaTransaccion(it, TE_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			valor = valor.replaceAll("[|H|M|N]", "\\#");
			valor = valor.replace("O", "0");
			rsp.setTe(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaTransaccion(it, TR_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			valor = valor.replaceAll("[|H|M|N]", "\\#");
			valor = valor.replace("O", "0");
			rsp.setTr(valor);
		}
		
		it = lineas.listIterator();
		valor = detectaFecha(it);
		rsp.setFecha(valor);
		
		it = lineas.listIterator();
		valor = detectaHora(it);
		rsp.setHora(valor);
		
		validarTicket(rsp);
		rsp.setTransaccion( rsp.getTda() + rsp.getOp() + rsp.getTe() + rsp.getTr() );
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

	private String detectaPattern( ListIterator<String> it, String pattern ) {
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

			Pattern p = Pattern.compile(WALMART_FECHA_PATTERN);
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

			Pattern p = Pattern.compile(WALMART_HORA_PATTERN);
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

			search: for (Map.Entry<String, String> entry : WALMART_FOOTERS.entrySet()) {
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
	
	private void validarTicket(WalmartTicketRSP rsp) throws TicketException {
		if ( rsp.getTda() != null && rsp.getOp() != null &&
				rsp.getTe() != null && rsp.getTr() != null
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
