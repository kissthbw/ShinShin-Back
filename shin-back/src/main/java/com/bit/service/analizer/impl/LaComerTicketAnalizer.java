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
public class LaComerTicketAnalizer implements TicketAnalizer {
	
	private static final Logger log = LoggerFactory.getLogger(LaComerTicketAnalizer.class);

	private static final String ID_TIENDA_CATALOGO_PATTERN = "LACOMER";
	
	private static final String ID_LACOMER_CP_FISCAL = "ID_LACOMER_CP_FISCAL";
	private static final String ID_LACOMER_CP_TIENDA = "ID_LACOMER_CP_TIENDA";
	private static final String ID_LACOMER_FECHA_PATTERN = "ID_LACOMER_FECHA_PATTERN";
	private static final String ID_LACOMER_HORA_PATTERN =   "ID_LACOMER_HORA_PATTERN";
	private static final String ID_SUCURSAL =   "ID_SUCURSAL";
	private static final String ID_SERIAL_NUMBER =   "ID_SERIAL_NUMBER";
	
	private String CP_FISCAL_PATTERN = "C.P.\\s[0-9]+";
	private String CP_TIENDA_PATTERN = "CP.[0-9]+";
	private String LACOMER_FECHA_PATTERN = "(0[1-9]|[12][0-9]|3[01])[/][a-zA-Z]+[/][0-9]{2}";
	private String LACOMER_HORA_PATTERN = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
	private String SUCURSAL = "[0-9]+\\s[0-9]+\\s[0-9]+\\s[0-9]+";
//	private String SERIAL_NUMBER = "[0-9]{5}";
	
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
		rsp.setTienda("LACOMER");
		rsp.setTieneCB(true);
		
		Map<String, String> patternMap = analizer.obtieneCatalogoPattern( ID_TIENDA_CATALOGO_PATTERN );
		if( !patternMap.isEmpty() ) {
			CP_FISCAL_PATTERN = patternMap.get( ID_LACOMER_CP_FISCAL ) != null ? patternMap.get( ID_LACOMER_CP_FISCAL ) : CP_FISCAL_PATTERN;
			CP_TIENDA_PATTERN = patternMap.get( ID_LACOMER_CP_TIENDA ) != null ? patternMap.get( ID_LACOMER_CP_TIENDA ) : CP_TIENDA_PATTERN;
			
			LACOMER_FECHA_PATTERN = patternMap.get( ID_LACOMER_FECHA_PATTERN ) != null ? patternMap.get( ID_LACOMER_FECHA_PATTERN ) : LACOMER_FECHA_PATTERN;
			LACOMER_HORA_PATTERN = patternMap.get( ID_LACOMER_HORA_PATTERN ) != null ? patternMap.get( ID_LACOMER_HORA_PATTERN ) : LACOMER_HORA_PATTERN;
			SUCURSAL = patternMap.get( ID_SUCURSAL ) != null ? patternMap.get( ID_SUCURSAL ) : SUCURSAL;
//			SERIAL_NUMBER = patternMap.get( ID_SERIAL_NUMBER ) != null ? patternMap.get( ID_SERIAL_NUMBER ) : SERIAL_NUMBER;

		}
		
		String valor = "";
		List<Integer> posList = new ArrayList<Integer>();

		ListIterator<String> it = lineas.listIterator();
		int depuraFooterPos = -1;
		it = lineas.listIterator();
		int pos = detectaPosicionTransaccion(it, LACOMER_FECHA_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, LACOMER_HORA_PATTERN);
		if (pos != -1) {
			posList.add( pos );
		}
		
		it = lineas.listIterator();
		pos = detectaPosicionTransaccion(it, SUCURSAL);
		if (pos != -1) {
			posList.add( pos );
		}
		
		//Esta el CP fiscal: C.P. #####
		//Esta el CP de la tienda: CP:#####
		it = lineas.listIterator();
		valor = detectaPattern(it, CP_FISCAL_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			rsp.setCpFiscal(valor);
		}
				
		it = lineas.listIterator();
		valor = detectaPattern(it, CP_TIENDA_PATTERN);
		if ( !"".equalsIgnoreCase(valor) ) {
			rsp.setCpTienda(valor);
		}
		
		Collections.sort(posList);
		if( !posList.isEmpty() ) {
			depuraFooterPos = posList.get( posList.size() - 1 );
		}
		
		it = lineas.listIterator();
//		depuraHeader(it, posList.get(0));
		
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
		
//		validarTicket(rsp);
		
		rsp.setTransaccion( rsp.getTransaccion().replace(" ", "") );
		
		it = lineas.listIterator();
		depuraFooter(it, depuraFooterPos);
		lineas.removeIf( item -> item == null || "".equals(item.trim()) );
		
		it = lineas.listIterator();
//		depuraProductos(it);
		
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

			Pattern p = Pattern.compile(LACOMER_FECHA_PATTERN);
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

			Pattern p = Pattern.compile(LACOMER_HORA_PATTERN);
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
	
	
	
	private void depuraFooter(ListIterator<String> it, int depuraFooterPos) {
		//Borar desde depuraFooterPos hasta el final de la lista
		
		if( depuraFooterPos != -1 ) {
			
			int pos = 0;
			
			while (it.hasNext()) {
				String linea = it.next();
				if( pos >= depuraFooterPos ) {
					it.remove();
				}
				pos++;
				
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

			Pattern p = Pattern.compile("");
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
