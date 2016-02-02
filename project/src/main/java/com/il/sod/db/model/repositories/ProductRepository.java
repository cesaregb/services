package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {}