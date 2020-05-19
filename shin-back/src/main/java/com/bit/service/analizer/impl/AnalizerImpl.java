package com.bit.service.analizer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.dao.CatalogoTiendaPatternDAO;
import com.bit.exception.TicketException;
import com.bit.model.CatalogoTiendaPattern;
import com.bit.model.dto.response.OCRTicketRSP;
import com.bit.service.analizer.Analizer;
import com.bit.service.analizer.TicketAnalizer;
import com.bit.service.analizer.TicketAnalizerFactory;

@Service
public class AnalizerImpl implements Analizer {
	
	private static final Logger log = LoggerFactory.getLogger(AnalizerImpl.class);
	
	@Autowired
	private TicketAnalizerFactory factory;
	
	@Autowired
	private CatalogoTiendaPatternDAO catalogoTiendaPatternDAO;
	
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
		//Chedraui - Agregar a BD tambien -
		tiendas.put("CHEDRAUI","CHEDRAUI");
		tiendas.put("chedraui","CHEDRAUI");
		tiendas.put("CHE DRAUI","CHEDRAUI");
		tiendas.put("che draui","CHEDRAUI");
		tiendas.put("H-E-B","H-E-B");
		tiendas.put("h-e-b","H-E-B");
		tiendas.put("lnCoNNer","LaComer");
		tiendas.put("nConer","LaComer");
		tiendas.put("Hn Comer","LaComer");
		tiendas.put("laComer","LaComer");
		tiendas.put("lacomer","LaComer");
	}
	
	@Override
	public OCRTicketRSP analize(List<String> lineas, boolean fake) throws TicketException {
		
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
		
		if ( null != valor == !"".equals( valor ) ) {
//			TicketAnalizer analizer = TicketAnalizerFactory.getAnalizer(valor.toUpperCase());
			TicketAnalizer analizer = factory.getAnalizer( valor.toUpperCase() );
			
			if( null != analizer ) {
				rsp = analizer.analize(lineas);
			}
			else {
				Throwable t = new Throwable("No se poseee informacion suficiente para analizar");
				throw new TicketException("No se poseee informacion suficiente para analizar", t, 204);
			}
			
		}
		else {
			Throwable t = new Throwable("Formato de ticket no reconocido");
			throw new TicketException("Formato de ticket no reconocido", t, 500);
		}
		
		return rsp;
	}
	
	@Transactional
	@Override
	public Map<String, String> obtieneCatalogoPattern( String idTienda ){
		Map<String, String> patternMap = new HashMap<>();
		
		List<CatalogoTiendaPattern> list = catalogoTiendaPatternDAO.getPatternsByTienda( idTienda );
		for (CatalogoTiendaPattern c : list) {
			patternMap.put( c.getIdPattern(), c.getPattern());
		}
		
		return patternMap;
	}
	
	
	private static String detectarTienda( ListIterator<String> it ) {
		String valor = "";
		
		search:
		while( it.hasNext() ) {
			String linea = it.next();
			
			for (Map.Entry<String, String> entry : Utils.obtieneCatalogo().entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    
			    if( linea.toUpperCase().contains(key.toUpperCase()) ) {
			    	valor = value;
			    	log.info("Ticket de: {}", valor);
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
