package com.il.sod.db.dao;

import java.util.List;

import com.il.sod.db.model.entities.SocialNetwork;
import com.il.sod.exception.SODAPIException;

public interface SocialNetworkServiceDAO {
	public SocialNetwork create(SocialNetwork SocialNetwork);
	public SocialNetwork delete(int id) throws SODAPIException;
	public List<SocialNetwork> findAll();
	public SocialNetwork update(SocialNetwork SocialNetwork) throws SODAPIException;
	public SocialNetwork findById(int id);
}
