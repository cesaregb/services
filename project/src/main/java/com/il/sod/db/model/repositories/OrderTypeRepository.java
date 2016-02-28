package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.OrderType;

public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {}