package com.il.sod.model.entities;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.model.entities.Supply;
import com.il.sod.db.model.repositories.SupplyRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SupplyDaoTest extends SpringTestConfiguration {

  @Autowired
  SupplyRepository supplyRepository;

  @Test
  public void testFindSupplyById() {
    List<Supply> list = supplyRepository.findByIdSupplyType(1);
    Assert.assertThat("list should be more than 1", list.size(), Matchers.greaterThan(0));
  }

}