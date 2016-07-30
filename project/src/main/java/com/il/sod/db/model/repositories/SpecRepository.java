package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.Spec;

public interface SpecRepository extends JpaRepository<Spec, Integer> {
	
	@Query("SELECT s FROM Spec s WHERE s.primarySpec=true")
	public List<Spec> findAllPrimary();
	
	@Query("SELECT s FROM Spec s WHERE s.primarySpec=false")
	public List<Spec> findAllNotPrimary();
	
	@Query("SELECT s FROM Spec s WHERE s.primarySpec=:primarySpec")
	public List<Spec> findAllByPrimary(@Param("primarySpec") boolean primarySpec);
	
}