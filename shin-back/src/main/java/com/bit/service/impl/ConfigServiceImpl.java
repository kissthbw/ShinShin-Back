package com.bit.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.dao.CatalogoDiccionarioTiendasDAO;
import com.bit.model.CatalogoDiccionarioTiendas;
import com.bit.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	private static Logger log = LoggerFactory.getLogger( ConfigServiceImpl.class );
	
	@Autowired
	private CatalogoDiccionarioTiendasDAO diccionario;
	
	@Override
	@Transactional
	public void actualizaDiccionarioTiendas() throws Exception {
		List<CatalogoDiccionarioTiendas> list = diccionario.getCatalogoDiccionarioTiendas();
		Utils.cargaDiccionario(list);
		
		log.info("Nuevos valores");
		for (Map.Entry<String, String> entry : Utils.obtieneCatalogo().entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    log.info( "{}:{}", key, value );
		}

	}

}
