package com.il.sod.test.model.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.db.model.repositories.ServiceCategoryRepository;
import com.il.sod.mapper.SpecificObjectsMapper;
import com.il.sod.rest.dto.web.WServiceCategoryDTO;
import com.il.sod.test.config.SpringTestConfiguration;

@Ignore
public class ServiceDaoTest extends SpringTestConfiguration{
	
	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
//    @Test
//    public void test(){
//    	List<ServiceCategory> entities = serviceCategoryRepository.findAll();
//    	System.out.println("entities: " + entities.size());
//		List<ServiceCategoryDTO> result = entities.stream().map(i -> ServiceMapper.INSTANCE.map(i)).collect(Collectors.toList());
//		System.out.println("result: " + result.size());
//    }
    
    @Test
    public void test2(){
    	SpecificObjectsMapper.INSTANCE.setProductRepository(productRepository);
    	List<ServiceCategory> entities = serviceCategoryRepository.findAll();
    	System.out.println("entities: " + entities.size());
		List<WServiceCategoryDTO> result = entities.stream().map(i -> SpecificObjectsMapper.INSTANCE.map(i)).collect(Collectors.toList());
		System.out.println("result: " + result.size());
    }
}