package com.il.sod.db.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.Shop;
import com.il.sod.db.model.repositories.ShopRepository;

@Service
public class SocialNetworkDao extends GenericDaoImpl<Shop, Integer>{

	@Resource
	private ShopRepository sRepository;
	
	public SocialNetworkDao(){
		System.out.println("instantiating the CORRET service!!!");
		this.setRepository(sRepository);
	}
}
