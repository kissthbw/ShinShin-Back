package com.bit.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.Valoracion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ValoracionDAOTest {

	@Autowired
	private ValoracionDAO valoracionDAO;

	@Transactional
	@Test
	public void crudTest() {
		Valoracion v = valoracionDAO.findByPK(1L);
		System.out.println(v.getValoracion());
	}
}
