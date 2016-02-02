package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {}