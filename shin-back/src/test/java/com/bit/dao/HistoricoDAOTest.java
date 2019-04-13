package com.bit.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.Historico;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class HistoricoDAOTest {

	@Autowired
	private HistoricoDAO historicoDAO;

	@Transactional
	@Test
	public void crudTest() {
		Historico h = historicoDAO.findByPK(1L);
		System.out.println(h.getTotalCompras());
	}

}
