package com.bit.common;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bit.exception.TicketException;
import com.bit.model.dto.response.OCRTicketRSP;

public class OxxoTicketAnalizer implements TicketAnalizer {

	private static final String OXXO_FECHA_PATTERN = "(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](19|20)\\d\\d";
	private static final String OXXO_HORA_PATTERN = "(\\d\\d:\\d\\d)";
	private static final String OXXO_PRODUCTOS_PATTERN = "[a-zA-Z0-9]*\\.?\\d*\\.?\\d*";
	private static final String OXXO_FOLIO_VENTA = "[a-zA-Z]+\\s?[a-zA-Z]+:[0-9]+";
	private static final String OXXO_ID_VENTA = "ID[=|a-zA-Z0-9]+";
	private static final String OXXO_CIFRAS = "^-?\\d*\\.{1,1}\\d+$";
	private static final String OXXO_CAJA = "\\b[0-9]+\\b";

	private static Map<String, String> OXXO_HEADERS = new HashMap<>();
	static {
		OXXO_HEADERS.put("PERIFERICO", "PERIFERICO");
		OXXO_HEADERS.put("PERIFERICO MEX", "PERIFERICO MEX");
		OXXO_HEADERS.put("PERIFERIC0", "PERIFERIC0");
		OXXO_HEADERS.put("C.P.", "C.P.");
		OXXO_HEADERS.put("Feginen", "Feginen");
		OXXO_HEADERS.put("Reginen", "Reginen");
		OXXO_HEADERS.put("Edtsan", "Edtsan");
		OXXO_HEADERS.put("Colonia", "Colonia");
		OXXO_HEADERS.put("Nuev", "Nuev");
	}

	private static Map<String, String> OXXO_FOOTERS = new HashMap<>();
	static {
		OXXO_FOOTERS.put("Total", "Total");
		OXXO_FOOTERS.put("Totai", "Total");
		OXXO_FOOTERS.put("/100", "/100");
	}

	@Override
	public OCRTicketRSP analize(List<String> lineas) throws TicketException{

		OCRTicketRSP rsp = new OCRTicketRSP();
		String valor = null;

		rsp.setTienda("OXXO");
		rsp.setTieneCB(false);

		ListIterator<String> it = lineas.listIterator();
		valor = detectaFecha(it);
		rsp.setFecha(valor);

		it = lineas.listIterator();
		valor = detectaHora(it);
		rsp.setHora(valor);

		it = lineas.listIterator();
		depuraHeader(it);

		it = lineas.listIterator();
		depuraFooter(it);

		it = lineas.listIterator();
		depuraFooter(it);

		it = lineas.listIterator();
		String folio = detectaPatternAndDelete(it, OXXO_FOLIO_VENTA, false);

		it = lineas.listIterator();
		String id = detectaPatternAndDelete(it, OXXO_ID_VENTA, false);

		it = lineas.listIterator();
		detectaPatternAndDelete(it, OXXO_CIFRAS, true);

		it = lineas.listIterator();
		depuraElementosVacios(it);

//		it = lineas.listIterator();
//		String caja = detectaPatternAndDelete(it, OXXO_CAJA, true);

		rsp.setLineas(lineas);

		return rsp;
	}

	private static void depuraHeader(ListIterator<String> it) {

		while (it.hasNext()) {
			String linea = it.next();

			search: for (Map.Entry<String, String> entry : OXXO_HEADERS.entrySet()) {
				String key = entry.getKey();

				if (linea.contains(key)) {
					it.remove();
					break search;
				}
			}
		}

	}

	private static void depuraFooter(ListIterator<String> it) {
		boolean borrar = false;
		while (it.hasNext()) {
			String linea = it.next();

			if (borrar) {
				it.remove();
				continue;
			}

			search: for (Map.Entry<String, String> entry : OXXO_FOOTERS.entrySet()) {
				String key = entry.getKey();

				// Comenzar a borrar el resto de las lineas
				if (linea.contains(key)) {
					borrar = !borrar;
					it.remove();
					break search;
				}

			}

		}

	}

	private static String detectaFecha(ListIterator<String> it) {

		String valor = "";

		search: while (it.hasNext()) {
			String linea = it.next();

			Pattern p = Pattern.compile(OXXO_FECHA_PATTERN);
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

	private static String detectaHora(ListIterator<String> it) {
		String valor = "";

		search: while (it.hasNext()) {
			String linea = it.next();

			Pattern p = Pattern.compile(OXXO_HORA_PATTERN);
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

	private static String detectaPatternAndDelete(ListIterator<String> it, String pattern, boolean delete) {
		String valor = "";

		search: while (it.hasNext()) {
			String linea = it.next();

			if ("".equals(linea.trim())) {
				it.remove();
				continue;
			}

			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(linea.trim());

			while (m.find()) {
				valor = m.group();
				linea = linea.replace(valor, "").trim();

				if ("".equals(linea.trim())) {
					it.remove();
				} else {
					it.set(linea);
				}
				if (!delete) {
					break search;
				}

			}
		}

		return valor;
	}

	private static void depuraElementosVacios(ListIterator<String> it) {
		while (it.hasNext()) {
			String linea = it.next();

			if ("".equals(linea.trim())) {
				it.remove();
			}
		}
	}

	private static String detectaPosibleProducto(String linea) {
		Pattern p = Pattern.compile(OXXO_PRODUCTOS_PATTERN);
		Matcher m = p.matcher(linea);
		String valor = "";

		while (m.find()) {
			valor = m.group();
			System.out.println("Valor: " + valor);
		}

		return valor;
	}

}
