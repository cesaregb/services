package com.il.sod.utils;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.model.entities.Client;
import com.il.sod.services.email.EmailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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