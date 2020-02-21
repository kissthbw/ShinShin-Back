package com.bit.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.service.ConfigService;

@RestController
@RequestMapping("/config")
public class ConfigRestController {
	
	private static Logger log = LoggerFactory.getLogger( ConfigRestController.class );
	
	@Autowired
	private ConfigService configService;
	
	@CrossOrigin
	@GetMapping("")
	public @ResponseBody ResponseEntity<String> actualizaDiccionarioTiendas() throws Exception {
		
		log.info("Actualizando diccionario de tiendas");

		configService.actualizaDiccionarioTiendas();
		
		return new ResponseEntity<String>("Info actualizada", HttpStatus.OK);
	}
	
	@ExceptionHandler({ Exception.class })
    public @ResponseBody ResponseEntity<String> handleException( Exception e ) {
		log.error( "Exception", e );
		return new ResponseEntity<String>("Error al atender peticion", HttpStatus.BAD_GATEWAY);
    }
}
