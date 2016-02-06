package com.il.sod.test.model.entities;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.db.dao.IDAO;
import com.il.sod.db.dao.SocialNetworkServiceDAO;
import com.il.sod.db.model.entities.SocialNetwork;
import com.il.sod.db.model.repositories.SocialNetworkRepository;
import com.il.sod.services.MyService;
import com.il.sod.test.config.SpringTestConfiguration;

@Ignore
public class ShopDaoImplTest extends SpringTestConfiguration{
	
	@BeforeClass
	public static void setup() {
		System.out.println("hola!!!");
	}
	@AfterClass
	public static void teardown() {
		System.out.println("adios!!!");
	}
	
	@Autowired
	MyService sampleService;
	
	@Autowired
	private IDAO<SocialNetwork, Integer> socialNetworkGenericDaoImpl;
	
	@Autowired
	private IDAO<SocialNetwork, Integer> genericDaoImpl;
	
	@Autowired
	private SocialNetworkServiceDAO socialNetworkServiceDAO;
	
	@Resource
	private SocialNetworkRepository shopRepository;
	
    @Test
    public void testDI(){
    	genericDaoImpl.setRepository(shopRepository);
    	SocialNetwork entity = new SocialNetwork();
    	entity.setDomain("domain");
    	entity.setName("name");
		System.out.println("===> " + socialNetworkGenericDaoImpl.findById(6));
    }
    
}