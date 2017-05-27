package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.PriceAdjustment;
import com.il.sod.db.model.repositories.PriceAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAdjustmentDAO {
	
	@Autowired
	PriceAdjustmentRepository priceAdjustmentRepository;
	
	public List<PriceAdjustment> findByName(String name) {
		return priceAdjustmentRepository.findByName("%" + name + "%");
	}
	public List<PriceAdjustment> findByPriceAdjustmentType(int idPriceAdjustmentType) {
		return priceAdjustmentRepository.findByPriceAdjustmentType(idPriceAdjustmentType);
	}
}
