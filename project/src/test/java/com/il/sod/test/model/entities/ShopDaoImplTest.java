package com.il.sod.test.model.entities;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.il.sod.db.dao.ShopServiceDAO;
import com.il.sod.db.model.entities.SocialNetwork;
import com.il.sod.db.model.repositories.ShopRepository;
import com.il.sod.services.MyService;
import com.il.sod.test.config.SpringTestConfiguration;

public class ShopDaoImplTest extends SpringTestConfiguration{
 
	@Autowired
	MyService sampleService;
	
//	@Autowired
//	private GenericDaoImpl<Shop, Integer> genericDaoImpl;
	
	@Autowired
	private ShopServiceDAO shopServiceDAO;
	
	@Value("${db.url}")
	private String dbUrl;
 
    @Test
    public void testDI(){
//    	genericDaoImpl.setRepository(repository);
    	System.out.println("===> dburl: " + dbUrl);
    	SocialNetwork entity = new SocialNetwork();
    	entity.setDomain("domain");
    	entity.setName("name");
		System.out.println("===> " + shopServiceDAO.findById(6));
    }
    
}