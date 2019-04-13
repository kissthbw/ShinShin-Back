package com.bit.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.PagoPayPal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class PagoPayPalDAOTest {

	@Autowired
	private PagoPayPalDAO pagoPayPalDAO;

	@Transactional
	@Test
	public void crudTest() {
		PagoPayPal pp = pagoPayPalDAO.findByPK(1L);
		System.out.println(pp.getCodigoCuenta());
	}
}
