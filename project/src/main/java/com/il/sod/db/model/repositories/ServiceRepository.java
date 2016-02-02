package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {}