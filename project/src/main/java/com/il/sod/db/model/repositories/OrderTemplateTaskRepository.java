package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.OrderTemplateTask;

public interface OrderTemplateTaskRepository extends JpaRepository<OrderTemplateTask, Integer> {}