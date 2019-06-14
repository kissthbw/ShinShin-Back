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
import com.bit.model.CatalogoMediosBonificacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class CatalogoMediosBonificacionDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogoMediosBonificacionDAOTest.class);
	
	@Autowired
	private CatalogoMediosBonificacionDAO catalogoMediosBonificacionDAO;

	@Transactional
	@Test
	public void crudTest() {
		CatalogoMediosBonificacion cmb = catalogoMediosBonificacionDAO.findByPK(1L);
		System.out.println(cmb.getNombreMedioBonificacion());
	}

	@Transactional
	@Test
	@Rollback(false)
	public void saveCatalogoMediosBonificacion() {
		CatalogoMediosBonificacion catalogoMediosBonificacion = new CatalogoMediosBonificacion();
		catalogoMediosBonificacion.setIdCatalogoMedioBonificacion(1l);
		catalogoMediosBonificacion.setNombreMedioBonificacion("PayPal");

		catalogoMediosBonificacionDAO.save(catalogoMediosBonificacion);
	}

}
