package com.bit.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.communication.FireBaseAdmin;
import com.bit.model.dto.request.NotificationRQT;
import com.bit.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	FireBaseAdmin fireBaseAdmin;
	
	@PostMapping("/toDevice")
	public void sendMessageToDevice( @RequestBody NotificationRQT rqt ) {
		fireBaseAdmin.sendPushNotificationToDevice(rqt.getDeviceToken(), rqt.getMessage());
	}
}
