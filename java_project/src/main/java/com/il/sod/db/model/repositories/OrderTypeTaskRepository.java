package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.OrderTypeTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTypeTaskRepository extends JpaRepository<OrderTypeTask, Integer> {
}