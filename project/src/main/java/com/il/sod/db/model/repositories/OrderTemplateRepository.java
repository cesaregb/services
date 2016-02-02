package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.OrderTemplate;

public interface OrderTemplateRepository extends JpaRepository<OrderTemplate, Integer> {}