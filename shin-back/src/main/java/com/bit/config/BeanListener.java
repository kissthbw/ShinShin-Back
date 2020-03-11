package com.bit.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.dao.CatalogoDiccionarioTiendasDAO;
import com.bit.dao.ConfigDAO;
import com.bit.model.CatalogoDiccionarioTiendas;
import com.bit.model.Config;

@Component
public class BeanListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(BeanListener.class);
	
	@Autowired
	private CatalogoDiccionarioTiendasDAO diccionario;
	
	@Autowired
	private ConfigDAO configDAO;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("Cargando diccionario");
		List<CatalogoDiccionarioTiendas> list = diccionario.getCatalogoDiccionarioTiendas();
		Utils.cargaDiccionario(list);
		
		log.info( "Leyendo configuracion" );
		Config c = configDAO.findByPK("FCM_KEY");
		byte[] data = Base64.getDecoder().decode( c.getKeyData() );
		InputStream inputStream = new ByteArrayInputStream(data);
		Utils.setCredentialsStream( inputStream );
	}

}
