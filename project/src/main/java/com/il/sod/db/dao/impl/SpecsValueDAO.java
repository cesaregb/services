package com.il.sod.db.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.db.model.repositories.SpecsValueRepository;

@Service
public class SpecsValueDAO {

	@Autowired
	SpecsValueRepository specsValueRepository;

	public List<SpecsValue> findBySpec(Integer idSpec) {
		return specsValueRepository.findBySpec(idSpec);
	}

}
