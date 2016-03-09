package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.OrderTypeTask;

public interface OrderTypeTaskRepository extends JpaRepository<OrderTypeTask, Integer> {}