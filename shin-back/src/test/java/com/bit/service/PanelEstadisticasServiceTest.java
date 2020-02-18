package com.bit.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.dto.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class PanelEstadisticasServiceTest {
	
	@Autowired
	private ReportService reportService;
	
	@Test
	@Transactional
	public void buscarrestaurarPasswordLink() {
		
		Map<String, List<Item>> result = reportService.getBonificacionesRecargasInfo();
		System.out.println( result );
	}
	
	
}
