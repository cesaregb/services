package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends DeletableRepository<Product, Integer> {

  @Query("SELECT s FROM Product s WHERE s.deleted=0")
  public List<Product> findAllActive();

  @Query("SELECT s FROM Product s WHERE s.name LIKE :name")
  public List<Product> findByName(@Param("name") String name);

  @Query("SELECT s FROM Product s WHERE s.productType.idProductType=:idProductType")
  public List<Product> findByProductType(@Param("idProductType") Integer idProductType);

}