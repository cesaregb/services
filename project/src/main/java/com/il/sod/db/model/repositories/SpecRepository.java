package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Spec;

public interface SpecRepository extends JpaRepository<Spec, Integer> {
	
	
}