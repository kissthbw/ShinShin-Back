package com.bit.communication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class FireBaseAdminTest {
	
	@Autowired
	FireBaseAdmin fireBaseAdmin;
	
	@Test
	public void sendPushNotificactionTest() {
		fireBaseAdmin.sendPushNotificationToDevice("dZP_oT_pRz0:APA91bFU4FP6Pk1RN8YDuoZMGcdnvJaeWgGp1FWnXL_e12odbALwQN_Rp6tW0Jo957GtAwjPkpnMCpYTUUiv8jhv5F-xeFWgMaYQZtUxFJkjana6xKt6Zclw1c_z7qPuvRZSHJ-fge3R",
				"Buenas noches");
	}
}
