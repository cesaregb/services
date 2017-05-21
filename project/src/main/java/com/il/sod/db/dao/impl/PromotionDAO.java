package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.PriceAdjustment;
import com.il.sod.db.model.repositories.PriceAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionDAO {
	
	@Autowired
	PriceAdjustmentRepository promotionRepository;
	
	public List<PriceAdjustment> findByName(String name) {
		return promotionRepository.findByName("%" + name + "%");
	}
	public List<PriceAdjustment> findByPromotionType(int idPromotionType) {
		return promotionRepository.findByPromotionType(idPromotionType);
	}
}
