package com.il.sod.model.entities;

import com.il.sod.db.model.entities.Supply;
import com.il.sod.db.model.repositories.SupplyRepository;
import com.il.sod.config.SpringTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SupplyDaoTest extends SpringTestConfiguration{
	
	@Autowired
	SupplyRepository supplyRepository;
	
    @Test
    public void test(){
    	List<Supply> l = supplyRepository.findByIdSupplyType(1);
    	System.out.println("===> " + l.size());
    }
    
}