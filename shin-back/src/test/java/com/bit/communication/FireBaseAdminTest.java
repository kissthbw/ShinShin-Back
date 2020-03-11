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
		fireBaseAdmin.sendPushNotificationToDevice("c92M8s_qOyc:APA91bEHJ3KLEEX8UVbIv4GsO2VTsPmBbIodr__se3g_UFON_hkO2XVQEDtgNjTeP0RT_qoZ1XWNF2ifoVIOUcGhUjwFGWB2LKkt2LAtp62h_XudIQEwt-BJs1eECgTQ3AU2FLr9YUt3",
				"Mensaje de prueba");
	}
}
