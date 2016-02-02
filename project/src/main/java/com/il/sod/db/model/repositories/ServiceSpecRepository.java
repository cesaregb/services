package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.ServiceSpec;

public interface ServiceSpecRepository extends JpaRepository<ServiceSpec, Integer> {}