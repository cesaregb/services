package com.il.sod.db.dao;

import java.util.List;

import com.il.sod.db.model.entities.Client;

public interface IClientDAO {
	
	public abstract List<Client> findByEmail(String email);
	
	public abstract List<Client> findByToken(String token);
	
	public abstract List<Client> findByAddress(Integer idAddress);
	
}
