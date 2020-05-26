package com.bit.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.CatalogoMarca;
import com.bit.model.Proveedor;
import com.bit.model.Usuario;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.InformacionDashboardProveedorRSP;
import com.bit.service.ProveedorService;
import com.bit.service.impl.EstadisticasServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProveedorDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(ProveedorDAOTest.class);
	
	@Autowired
	private ProveedorDAO proveedorDAO;
	
	@Autowired
	private ProveedorService proveedorService;

	@Transactional
	@Test
	public void totales() {
		
		Double t = proveedorDAO.obtieneTotalBonificacionesPorMarca( 10L );
		log.info( "Bonificaciones totales: {}", t );
	}
	
	@Transactional
	@Test
	public void totalesEmpresaUsuarios() {
		
		List<Usuario> list = proveedorDAO.obtieneInformacionUsuariosEmpresa( 7l );
		proveedorDAO.obtieneListaUsuariosEmpresa( 7L );
		
		long totalM = list.stream().filter( i -> i.getIdCatalogoSexo() == 1 ).count();
		long totalF = list.stream().filter( i -> i.getIdCatalogoSexo() == 2 ).count();
		
		double promedioEdad = list
			    .stream()
			    .mapToInt(Usuario::getEdad)
			    .average()
			    .getAsDouble();
		
		//count = 100%
		//totalM
		
		log.info( "Total: {}", list.size() );
		log.info( "Total de hombres: {} %", ( totalM * 100 ) /  list.size() );
		log.info( "Total de mejres: {} %", ( totalF * 100 ) /  list.size() );
		log.info( "Promedio de edad: {}", promedioEdad );
		
		for( Usuario u : list ) {
			log.info( u.getNombre() + " - " + u.getEdad() + " - " + u.getIdCatalogoSexo() );
		}
	}

	@Transactional
	@Test
	public void estadisticas() {
		Proveedor p = new Proveedor();
		p.setId(7L);
		
		EstadisticasGeneralRSP rsp = proveedorService.obtieneEstadisticasEmpresaGeneral(p, null, null);
		

	}
	
	@Transactional
	@Test
	public void estadisticasProductos() {
		Proveedor p = new Proveedor();
		CatalogoMarca cm = new CatalogoMarca();
		cm.setIdCatalogoMarca( 7L );
		p.setMarca(cm);
		p.setId(7L);
		
		InformacionDashboardProveedorRSP rsp = proveedorService.obtieneTotalesDashboardEmpresaProductos(p);
		

	}
	
	@Transactional
	@Test
	public void obtieneInfoReporteEmpresaUsuarios() {
		Proveedor p = new Proveedor();
		CatalogoMarca cm = new CatalogoMarca();
		cm.setIdCatalogoMarca( 7L );
		p.setMarca(cm);
		p.setId(7L);
		
		proveedorService.obtieneInfoReporteEmpresaUsuarios(p);
		

	}
}
