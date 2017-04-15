package com.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc                                            // This replaces <mvc:annotation-driven /> in xml config file.
@ComponentScan(basePackages="com.demo.controllers")      // This replaces <context:component-scan base-package="com.demo.controllers" /> in xml config file.
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter { // extends WebMvcConfigurerAdapter to take advantage of the default configurations.

	
	// The following method replaces the view resolver of the  xml config:
	@Bean
	public InternalResourceViewResolver getIRVR(){
		System.out.println("Setting up view resolver");
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("WEB-INF/jsp/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
	
	// The following method replaces the file uploading of the xml config: 
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		System.out.println("Setting up multipartResolver for uploading files");
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setMaxUploadSize(5252880);
	    //resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}	
	
	// The following method replaces the static resources access of the xml config: 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		System.out.println("Setting up resources");
		registry.addResourceHandler("/myResources/**").addResourceLocations("/resources/");
	}
	
}
