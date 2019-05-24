package com.bit.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.MediosBonificacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class MediosBonificacionDOATest {

	@Autowired
	private MediosBonificacionDAO mediosBonificacionDAO;

	@Transactional
	@Test
	public void crudTest() {
		MediosBonificacion mb = mediosBonificacionDAO.findByPK(1L);
		System.out.println(mb.getCatalogoMediosBonificacion());
	}

	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		MediosBonificacion item = new MediosBonificacion();
		item.setIdMediosBonificacion(2l);
		item.setCuentaMedioBonificacion("");
		item.setCompaniaMedioBonificacion("");
		
		mediosBonificacionDAO.save(item);
	}

}
