package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Promotion;
import com.il.sod.db.model.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionDAO {
	
	@Autowired
	PromotionRepository promotionRepository;
	
	public List<Promotion> findByName(String name) {
		return promotionRepository.findByName("%" + name + "%");
	}
	public List<Promotion> findByPromotionType(int idPromotionType) {
		return promotionRepository.findByPromotionType(idPromotionType);
	}
}
