package com.il.sod.model.entities;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.model.entities.PriceAdjustment;
import com.il.sod.db.model.repositories.PriceAdjustmentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PriceAdjustmentTest extends SpringTestConfiguration {
  @Autowired
  PriceAdjustmentRepository priceAdjustmentRepository;

  @Test
  public void getAllPrices() {
    List<PriceAdjustment> list = priceAdjustmentRepository.findAll();
    list.forEach(System.out::println);
  }

}