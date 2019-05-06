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
import com.bit.model.HistoricoMediosBonificacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class HistoricoMediosBonificacionDAOTest {

	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;

	@Transactional
	@Test
	public void crudTest() {
		HistoricoMediosBonificacion hmb = historicoMediosBonificacionDAO.findByPK(1L);
		System.out.println(hmb.getCantidadBonificacion());
	}
	
	

}
