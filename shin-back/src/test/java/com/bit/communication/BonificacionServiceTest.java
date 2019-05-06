package com.bit.communication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.config.WebConfig;
import com.bit.exception.CommunicationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class BonificacionServiceTest {

	@Autowired
	private BonificacionService bonificacionService;
	
	@Test
	public void test() {
		try {
			bonificacionService.bonificacionPayPal(null);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
