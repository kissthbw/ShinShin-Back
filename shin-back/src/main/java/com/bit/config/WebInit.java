package com.bit.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		String temp = System.getProperty("catalina.base") +  File.separator + "temp";
		File folder = new File(temp);
		
		if( !folder.exists() ){
			folder.mkdir();
		}
		
		registration.setMultipartConfig(new MultipartConfigElement(temp, 20097152, 40194304, 0));
	}
}
