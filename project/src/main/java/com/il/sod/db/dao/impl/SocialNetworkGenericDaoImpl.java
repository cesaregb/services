package com.il.sod.db.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.SocialNetwork;
import com.il.sod.db.model.repositories.SocialNetworkRepository;

@Service
public class SocialNetworkGenericDaoImpl extends GenericDaoImpl<SocialNetwork, Integer>{
	
	@Autowired
	public SocialNetworkGenericDaoImpl(SocialNetworkRepository sRepository){
		super (sRepository);
		System.out.println("instantiating the CORRET service!!!");
	}
}
