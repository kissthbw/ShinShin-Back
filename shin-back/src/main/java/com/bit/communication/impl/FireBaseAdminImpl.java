package com.bit.communication.impl;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.communication.FireBaseAdmin;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidConfig.Priority;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FireBaseAdminImpl implements FireBaseAdmin {

	private static final Logger log = LoggerFactory.getLogger(FireBaseAdminImpl.class);

	
//	public FireBaseAdminImpl() {
//
//		
//		File f = new File( "runtime.properties" );
//		log.info( "Existe archivo: {}", f.exists() );
//		
//		FileInputStream serviceAccount;
//		try {
//			
//			log.info( "Leyendo configuracion" );
//			Config c = configDAO.findByPK("FCM_KEY");
//			byte[] data = Base64.getDecoder().decode( c.getKeyData() );
//			InputStream inputStream = new ByteArrayInputStream(data);
//			
//			serviceAccount = new FileInputStream(
//					"/Users/juanosorioalvarez/Documents/Bit" + "/ShinShin/Client-credentials/Firebase/SDK/"
//							+ "shingshing-69c1f-firebase-adminsdk-wn7il-c0a70c2ccc.json");
//			FirebaseOptions options = new FirebaseOptions.Builder()
//					.setCredentials(GoogleCredentials.fromStream( Utils.getCredentialsStream() ))
//					.setDatabaseUrl("https://shingshing-69c1f.firebaseio.com").build();
//
//			FirebaseApp.initializeApp(options);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void sendPushNotificationToDevice(String deviceToken, String notification) {
		
		log.info("Enviando notificacion a usuario con token: {}", deviceToken);
		
//		init();
		
		Map<String, String> data = new HashMap<>();
		data.put("id", "id");
		data.put("text", "Notification");
		
//		try {
//			send(data);
//		} catch (InterruptedException | ExecutionException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		Message message = Message.builder().putAllData(data).setToken( deviceToken )
				.setNotification(new Notification("ShingShing", notification)).build();

		
		try {
			String response = FirebaseMessaging.getInstance().sendAsync(message).get();
			log.info( "Respuesta: {}", response );
		} catch (Exception e) {
			log.error( "Error al enviar notifcacion", e );
		}
	}
	
	@Transactional
	private void init() {
		try {
			
				log.info( "Leyendo configuracion" );
//				Config c = configDAO.findByPK("FCM_KEY");
//				byte[] data = Base64.getDecoder().decode( c.getKeyData() );
//				InputStream inputStream = new ByteArrayInputStream(data);

				FirebaseOptions options = new FirebaseOptions.Builder()
						.setCredentials(GoogleCredentials.fromStream( Utils.getCredentialsStream() ))
						.setDatabaseUrl("https://shingshing-69c1f.firebaseio.com").build();

				FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void send(Map<String, String> data) throws InterruptedException, ExecutionException {
		String clientToken = "dZP_oT_pRz0:APA91bFU4FP6Pk1RN8YDuoZMGcdnvJaeWgGp1FWnXL_e12odbALwQN_Rp6tW0Jo957GtAwjPkpnMCpYTUUiv8jhv5F-xeFWgMaYQZtUxFJkjana6xKt6Zclw1c_z7qPuvRZSHJ-fge3R";

		AndroidConfig androidConfig = AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis())
				.setCollapseKey("personal").setPriority(Priority.HIGH)
				.setNotification(AndroidNotification.builder().setTag("personal").build()).build();

		ApnsConfig apnsConfig = ApnsConfig.builder()
				.setAps(Aps.builder().setCategory("personal").setThreadId("personal").build()).build();

		Message message = Message.builder().putAllData(data).setToken(clientToken)
				.setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
				.setNotification(new Notification("ShingShing", "Bonificacion aceptada")).build();

		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message: " + response);
	}
}
