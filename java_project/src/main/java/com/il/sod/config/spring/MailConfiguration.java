package com.il.sod.config.spring;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactory;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class MailConfiguration {

  @Bean
  public VelocityEngine velocityEngine() throws VelocityException, IOException {
    VelocityEngineFactory factory = new VelocityEngineFactory();
    Properties props = new Properties();
    props.put("resource.loader", "class");
    props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    factory.setVelocityProperties(props);
    return factory.createVelocityEngine();
  }
//	
//	@Bean
//	public VelocityEngine velocityEngine() throws VelocityException, IOException{
//		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
//		Properties props = new Properties();
//		props.put("resource.loader", "class");
//		props.put("class.resource.loader.class", 
//				  "org.apache.velocity.runtime.resource.loader." + 
//				  "ClasspathResourceLoader");
//		factory.setVelocityProperties(props);
//		
//		return factory.createVelocityEngine();
//	}

  @Value("${email.host}")
  private String host;

  @Value("${email.port}")
  private Integer port;

  @Value("${email.username}")
  private String emailUsername;

  @Value("${email.password}")
  private String emailPassword;

  @Bean
  public JavaMailSender javaMailService() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setJavaMailProperties(getMailProperties());
    javaMailSender.setUsername(emailUsername);
    javaMailSender.setPassword(emailPassword);
    return javaMailSender;
  }

  private Properties getMailProperties() {
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.starttls.enable", "false");
    properties.setProperty("mail.debug", "false");
    return properties;
  }
}
