package com.il.sod.db.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.il.sod.db.model.entities.Shop;
import com.il.sod.db.model.repositories.ShopRepository;
import com.il.sod.exception.SODAPIException;

import com.il.sod.db.dao.ShopServiceDAO;

@Service
public class ShopServiceDaoImpl implements ShopServiceDAO {

	@Resource
	private ShopRepository shopRepository;

	@Override
	@Transactional
	public Shop create(Shop shop) {
		Shop createdShop = shop;
		return shopRepository.save(createdShop);
	}

	@Override
	@Transactional
	public Shop findById(int id) {
		return shopRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor = SODAPIException.class)
	public Shop delete(int id) throws SODAPIException {
		Shop deletedShop = shopRepository.findOne(id);

		if (deletedShop == null)
			throw new SODAPIException("");

		shopRepository.delete(deletedShop);
		return deletedShop;
	}

	@Override
	@Transactional
	public List<Shop> findAll() {
		return shopRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor = SODAPIException.class)
	public Shop update(Shop shop) throws SODAPIException {
		Shop updatedShop = shopRepository.findOne(shop.getId());

		if (updatedShop == null)
			throw new SODAPIException("");

		updatedShop.setName(shop.getName());
		updatedShop.setEmplNumber(shop.getEmplNumber());
		return updatedShop;
	}
}