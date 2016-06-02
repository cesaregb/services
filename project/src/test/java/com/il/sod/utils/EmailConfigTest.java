package com.il.sod.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.db.model.entities.Client;
import com.il.sod.services.email.EmailService;
import com.il.sod.test.config.SpringTestConfiguration;

public class EmailConfigTest extends SpringTestConfiguration{
	
	@Autowired
	EmailService emailService;
	
    @Test
    public void test(){
    	Client client = new Client();
    	client.setName("Cesar");
    	client.setEmail("cesareg.borjon@gmail.com");
		emailService.send(client);
    }
}