package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.PriceAdjustment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceAdjustmentRepository extends DeletableRepository<PriceAdjustment, Integer> {

  @Query("SELECT s FROM PriceAdjustment s WHERE s.deleted=0")
  public List<PriceAdjustment> findAllActive();

  @Query("SELECT s FROM PriceAdjustment s WHERE s.name LIKE :name")
  public List<PriceAdjustment> findByName(@Param("name") String name);

  @Query("SELECT s FROM PriceAdjustment s WHERE s.priceAdjustmentType.idPriceAdjustmentType=:idPriceAdjustmentType")
  public List<PriceAdjustment> findByPriceAdjustmentType(@Param("idPriceAdjustmentType") Integer idPriceAdjustmentType);

}