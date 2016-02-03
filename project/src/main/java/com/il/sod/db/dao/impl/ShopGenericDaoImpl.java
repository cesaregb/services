package com.il.sod.db.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.Shop;
import com.il.sod.db.model.repositories.ShopRepository;

@Service
public class ShopGenericDaoImpl extends GenericDaoImpl<Shop, Integer>{
	
	@Autowired
	public ShopGenericDaoImpl(ShopRepository sRepository){
		super (sRepository);
		System.out.println("instantiating the CORRET service!!!");
	}
}
