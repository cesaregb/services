package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ServiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProductsRepository extends JpaRepository<ServiceProduct, Integer> {}