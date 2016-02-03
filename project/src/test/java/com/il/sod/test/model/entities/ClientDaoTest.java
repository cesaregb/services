package com.il.sod.test.model.entities;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.db.dao.IDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.test.config.SpringTestConfiguration;

import junit.framework.Assert;

public class ClientDaoTest extends SpringTestConfiguration{
	
	@Autowired
	IDAO<Client, Integer> genericDaoImpl;
	
	@Autowired
	ClientRepository clientRepository;
	
    @Test
    public void test(){
    	try{
    		genericDaoImpl.setRepository(clientRepository);
//    		Assert.assertTrue(create());
    		Assert.assertTrue(findAll());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public boolean create(){
    	Client entity = new Client();
    	entity.setUsername("username");
    	entity.setName("name");
    	entity.setLasName("last name");
    	entity.setPhoneNumber("number");
    	entity.setTwitter("twiiter");
		genericDaoImpl.create(entity);
    	return true;
    }
    
    public boolean findAll(){
    	genericDaoImpl.findAll();
    	return true;
    }
    
}