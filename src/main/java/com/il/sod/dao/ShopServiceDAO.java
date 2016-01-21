package com.il.sod.dao;

import java.util.List;

import com.il.sod.exception.SODAPIException;
import com.il.sod.model.entities.Shop;

public interface ShopServiceDAO {
	public Shop create(Shop shop);
	public Shop delete(int id) throws SODAPIException;
	public List<Shop> findAll();
	public Shop update(Shop shop) throws SODAPIException;
	public Shop findById(int id);
}
