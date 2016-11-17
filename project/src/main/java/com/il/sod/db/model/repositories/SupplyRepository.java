package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.Supply;

public interface SupplyRepository extends JpaRepository<Supply, Integer> {
	
	@Query("SELECT p FROM Supply p WHERE p.supplyType.idSupplyType=:idSupplyType")
	List<Supply> findByIdSupplyType(@Param("idSupplyType") Integer idSupplyType);
	
}