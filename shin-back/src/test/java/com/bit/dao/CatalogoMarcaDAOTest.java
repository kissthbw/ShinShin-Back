package com.bit.dao;

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
import com.bit.model.CatalogoMarca;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class CatalogoMarcaDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoMarcaDAOTest.class);
	
	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;

	@Transactional
	@Test
	public void crudTest() {
		
		CatalogoMarca cm = catalogoMarcaDAO.findByPK(1L);
		System.out.println(cm.getNombreMarca());
	}

	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		
		CatalogoMarca item = new CatalogoMarca();
		item.setNombreMarca("Chrome");
		
		catalogoMarcaDAO.save(item);
	}
	
}
