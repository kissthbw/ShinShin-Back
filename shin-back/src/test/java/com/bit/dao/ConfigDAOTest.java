package com.bit.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ConfigDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(ConfigDAOTest.class);

	@Autowired
	private ConfigDAO configDAO;

	@Rollback(value = false)
	@Transactional()
	@Test
	public void crudTest() {
		String file = "/Users/juanosorioalvarez/Documents/Bit/ShinShin/Client-credentials/Firebase/SDK/shingshing-69c1f-firebase-adminsdk-wn7il-c0a70c2ccc.json";
		
		try {
			byte[] data = Files.readAllBytes(new File(file).toPath());
			String base64 = Base64.getEncoder().encodeToString(data);
			Config c = new Config();
			c.setKeyName("FCM_KEY");
			c.setKeyValue("FCM_KEY_FILE");
			c.setKeyData(base64);
			configDAO.save(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void readFileFromDB() {
		Config c = configDAO.findByPK("FCM_KEY");
		byte[] data = Base64.getDecoder().decode( c.getKeyData() );
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		File targetFile = new File("/Users/juanosorioalvarez/Desktop");
	    
		try {

//			OutputStream out = new FileOutputStream(targetFile);
			Files.copy(inputStream, Paths.get("/Users/juanosorioalvarez/Desktop/test.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
}
