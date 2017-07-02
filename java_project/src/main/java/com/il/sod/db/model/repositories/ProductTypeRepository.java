package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductTypeRepository extends DeletableRepository<ProductType, Integer> {

  @Query("SELECT pt FROM ProductType pt join pt.serviceTypes sts WHERE sts.idServiceType=:idServiceType")
  public List<ProductType> findByServiceType(@Param("idServiceType") int idServiceType);


}