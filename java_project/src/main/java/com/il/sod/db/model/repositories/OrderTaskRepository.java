package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.OrderTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTaskRepository extends JpaRepository<OrderTask, Integer> {
}