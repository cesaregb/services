package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ServiceSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceSpecRepository extends JpaRepository<ServiceSpec, Integer> {
}