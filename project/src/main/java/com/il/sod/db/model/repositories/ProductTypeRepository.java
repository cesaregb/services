package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {}