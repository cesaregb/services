package com.il.sod.test.model.entities;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.il.sod.dao.ShopServiceDAO;
import com.il.sod.services.MyService;
import com.il.sod.test.config.SpringTestConfiguration;

@Ignore
public class ShopDaoImplTest extends SpringTestConfiguration{
 
	@Autowired
	MyService sampleService;
	
	@Autowired
	private ShopServiceDAO shopServiceDAO;
	
	@Value("${db.url}")
	private String dbUrl;
 
    @Test
    public void testDI(){
    	System.out.println("===> dburl: " + dbUrl);
    	System.out.println("===> " + shopServiceDAO.findById(6));
    }
    
}