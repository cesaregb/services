package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.db.model.repositories.SpecsValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecsValueDAO {

  @Autowired
  SpecsValueRepository specsValueRepository;

  public List<SpecsValue> findBySpec(Integer idSpec) {
    return specsValueRepository.findBySpec(idSpec);
  }

}
