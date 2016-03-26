package com.il.sod.test.model.entities;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.test.config.SpringTestConfiguration;

public class ProductDaoTest extends SpringTestConfiguration{
	
	@Autowired
	ProductRepository productRepository;
	
    @Test
    public void test(){
    	List<Product> l = productRepository.findByIdProductType(1);
    	System.out.println("===> " + l.size());
    }
    
}