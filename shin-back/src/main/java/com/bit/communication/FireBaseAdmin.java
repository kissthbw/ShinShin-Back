package com.bit.communication;

public interface FireBaseAdmin {
	void sendPushNotificationToDevice(String deviceToken, String notification);
}
