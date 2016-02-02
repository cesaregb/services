package com.il.sod.test.model.entities;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.il.sod.db.dao.IDAO;
import com.il.sod.db.dao.ShopServiceDAO;
import com.il.sod.db.model.entities.Shop;
import com.il.sod.db.model.entities.SocialNetwork;
import com.il.sod.db.model.repositories.ShopRepository;
import com.il.sod.services.MyService;
import com.il.sod.test.config.SpringTestConfiguration;

public class ShopDaoImplTest extends SpringTestConfiguration{
 
	@Autowired
	MyService sampleService;
	
	@Autowired
	private IDAO<Shop, Integer> socialNetworkDao;
	
	@Autowired
	private ShopServiceDAO shopServiceDAO;
	
	@Resource
	private ShopRepository shopRepository;
	
	@Value("${db.url}")
	private String dbUrl;
 
    @Test
    public void testDI(){
//    	genericDaoImpl.setRepository(shopRepository);
    	System.out.println("===> dburl: " + dbUrl);
    	SocialNetwork entity = new SocialNetwork();
    	entity.setDomain("domain");
    	entity.setName("name");
		System.out.println("===> " + socialNetworkDao.findById(6));
    }
    
}