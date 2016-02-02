package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {}