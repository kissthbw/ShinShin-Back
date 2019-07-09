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

import com.bit.common.Utils;
import com.bit.config.WebConfig;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class MediosBonificacionDOATest {
	
	private static final Logger log = LoggerFactory.getLogger(MediosBonificacionDOATest.class);
	
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
		CatalogoMediosBonificacion cat = new CatalogoMediosBonificacion();
		cat.setIdCatalogoMedioBonificacion(3L);
		Usuario u = new Usuario();
		u.setIdUsuario(1L);
		item.setAliasMedioBonificacion("Personal");
		item.setCuentaMedioBonificacion("5534714616");
		item.setCompaniaMedioBonificacion("TELCEL");
		item.setCatalogoMediosBonificacion(cat);
		item.setUsuario(u);
		try {
			System.out.println( Utils.objectToJSON(item) );
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediosBonificacionDAO.save(item);
	}

}
