package com.il.sod.db.dao;

import java.util.List;

import com.il.sod.db.model.entities.Shop;
import com.il.sod.exception.SODAPIException;

public interface ShopServiceDAO {
	
	public Shop create(Shop shop);
	public Shop delete(int id) throws SODAPIException;
	public List<Shop> findAll();
	public Shop update(Shop shop) throws SODAPIException;
	public Shop findById(int id);
}
