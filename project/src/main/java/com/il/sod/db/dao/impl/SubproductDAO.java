package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Subproduct;
import com.il.sod.db.model.repositories.SubproductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubproductDAO {
	
	@Autowired
	SubproductRepository subproductRepository;
	
	public List<Subproduct> findByName(String name) {
		return subproductRepository.findByName("%" + name + "%");
	}
}
