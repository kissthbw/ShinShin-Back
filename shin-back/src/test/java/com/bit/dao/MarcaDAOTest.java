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
import com.bit.model.Marca;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class MarcaDAOTest {

	@Autowired
	private MarcaDAO marcaDAO;

	@Transactional
	@Test
	public void crudTest() {
		Marca m = marcaDAO.findByPK(1L);
		System.out.println(m.getNombreMarca());
	}

	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		Marca item = new Marca();
		item.setNombreMarca("Roku");
		
		
		marcaDAO.save(item);
	}
	
}
