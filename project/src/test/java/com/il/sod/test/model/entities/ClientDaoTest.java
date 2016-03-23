package com.il.sod.test.model.entities;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.il.sod.db.dao.IClientDAO;
import com.il.sod.db.dao.IDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.test.config.SpringTestConfiguration;

public class ClientDaoTest extends SpringTestConfiguration{
	
	@Autowired
	IDAO<Client, Integer> genericDaoImpl;
	
	@Autowired
	private IClientDAO clientDAO;
	
	@Autowired
	ClientRepository clientRepository;
	
    @Test
    public void test(){
    	try{
//    		genericDaoImpl.setRepository(clientRepository);
//    		Assert.assertTrue(create());
//    		Assert.assertNotNull(SelfPublishKeyValues.INSTANCE.getProperty("selfpub.registration.api.port"));
//    		Assert.assertTrue(findAll());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void test2(){
//    	clientDao.findByEmail("cesareg.borjon@gmail.com");
//    	List<Client> c = clientDao.findAll();
//    	List<Client> c = clientReadsDAO.findByEmail("cesareg.borjon@gmail.com");
//    	List<Client> c = clientReadsDAO.findByToken("abcd");
    	List<Client> c = clientDAO.findByToken2("abcd");
    	System.out.println("*************************");
    	System.out.println("*************************");
    	for ( Client item : c ){
    		System.out.println("email: " + item.getEmail());
    		System.out.println("name: " + item.getName());
    	}
    	System.out.println("*************************");
    	System.out.println("*************************");
    }
    
    public boolean create(){
    	Client entity = new Client();
    	entity.setEmail("email");
    	entity.setName("name");
    	entity.setLastName("last name");
    	entity.setTwitter("twiiter");
		genericDaoImpl.create(entity);
    	return true;
    }
    
    @Transactional
    public boolean findAll(){
    	System.out.println("Hola!!!!");
    	List<Client> r = genericDaoImpl.findAll();
    	for (Client c : r){
    		System.out.println("email: " + c.getEmail());
    		System.out.println("size: " + c.getAccessKeys().size());
    	}
    	return true;
    }
    
}