package com.bit.dao;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.CatalogoTipoProducto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class CatalogoTipoProductoDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoTipoProductoDAOTest.class);
	
	@Autowired
	private CatalogoTipoProductoDAO catalogoTipoProductoDAO;
	
	@Autowired
	private CatalogoTiendaDAO catalogoTiendaDAO;
	
	@Transactional
	@Test
	public void crudTest() {
		
		CatalogoTipoProducto ctp = catalogoTipoProductoDAO.findByPK(1L);
		System.out.println(ctp.getNombreTipoProducto());
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		CatalogoTipoProducto item = new CatalogoTipoProducto();
		item.setNombreTipoProducto("Lacteos");
		
		catalogoTipoProductoDAO.save(item);
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void delete() {
		System.out.println( "Inicio: " + new Date() );
		
		CatalogoTipoProducto item = new CatalogoTipoProducto();
//		item.setIdCatalogoTipoProducto(1L);
		item = catalogoTipoProductoDAO.findByPK( 1L );
		item.setEstatus(false);
		
//		catalogoTipoProductoDAO.eliminaDepartamento(item);
		catalogoTipoProductoDAO.update(item);
		System.out.println( "Fin: " + new Date() );
	}
	
	@Test
	@Transactional
	public void totalDepartamentos() {
		BigInteger total = catalogoTipoProductoDAO.obtieneDepartamentosRegistrados();
		System.out.println("Departamentos: " + total);
	}
	
	@Test
	@Transactional
	public void obtieneResumenTiendas() {
		catalogoTiendaDAO.obtieneResumenTiendas();
	}
	
	@Test
	@Transactional
	public void obtieneResumenDeptos() {
		catalogoTipoProductoDAO.obtieneResumenDepartamento();
	}
	
	
}
