package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Supply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplyRepository extends DeletableRepository<Supply, Integer> {

  @Query("SELECT p FROM Supply p WHERE p.supplyType.idSupplyType=:idSupplyType")
  List<Supply> findByIdSupplyType(@Param("idSupplyType") Integer idSupplyType);

}