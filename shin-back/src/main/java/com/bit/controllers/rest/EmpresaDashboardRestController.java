package com.bit.controllers.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.CatalogoMarca;
import com.bit.model.Proveedor;
import com.bit.model.dto.Item;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.service.ProveedorService;

@RestController
@RequestMapping("/estadisticas/empresa")
public class EmpresaDashboardRestController { 
	
	private static final Logger log= LoggerFactory.getLogger(EmpresaDashboardRestController.class);
	
	@Autowired
	private ProveedorService proveedorService;
	
	/**
	 * 
	 * @param tipo, null si se requiere obtener consulta inicial, 1 para bonificaciones, 2 para para productos, 3 para usuarios
	 * @param categoria, null si se requiere obtener consulta inicial, d para dia, s para semana, m para mes
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/charts/dashboard")
	public @ResponseBody EstadisticasGeneralRSP obtieneEstadisticasEmpresaGeneral( @RequestParam String idMarca,
			@RequestParam(required = false) String tipo,
			@RequestParam(required = false) String categoria ) {
		
		log.info("Entrando a obtieneEstadisticasGeneral");
		
		Proveedor item = new Proveedor();
		item.setId( Long.parseLong( idMarca ) );
		
		EstadisticasGeneralRSP rsp = proveedorService.obtieneEstadisticasEmpresaGeneral( item, tipo, categoria );

		return rsp;
	}
	
	/**
	 * 
	 * @param tipo, null si se requiere obtener consulta inicial, 1 para bonificaciones, 2 para para productos, 3 para usuarios
	 * @param categoria, null si se requiere obtener consulta inicial, d para dia, s para semana, m para mes
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/charts/dashboard-usuario")
	public @ResponseBody List<Item> obtieneEstadisticasEmpresaTopCP( @RequestParam String idMarca ) {
		
		log.info("Entrando a obtieneEstadisticasGeneral");
		
		Proveedor item = new Proveedor();
		item.setId( Long.parseLong( idMarca ) );
		CatalogoMarca cm = new CatalogoMarca();
		cm.setIdCatalogoMarca( Long.parseLong( idMarca ) );
		item.setMarca(cm);
		
		List<Item> rsp = proveedorService.obtieneEstadisticasEmpresaTopCP(item);

		return rsp;
	}
	
}