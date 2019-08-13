package com.bit.communication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.config.WebConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class CloudinaryTest {
	
	@Test
	public void uploadTest() {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "shingshing",
				  "api_key", "657472936977876",
				  "api_secret", "cZ8wZWzSvTqXdqBO7P1e62xnzVY")); 
		
		File toUpload = new File("/Users/juanosorioalvarez/Downloads/Superama.png");
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/Superama", 
				   "overwrite", true
				);
		
		try {
			byte[] bFile = Files.readAllBytes(toUpload.toPath());
			Map uploadResult = cloudinary.uploader().upload(toUpload, params);
			String url = cloudinary.url().generate("shingshing/Superama.png");
			System.out.println(uploadResult);
			System.out.println(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
