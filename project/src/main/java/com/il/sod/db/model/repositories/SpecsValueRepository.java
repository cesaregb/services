package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.SpecsValue;

public interface SpecsValueRepository extends JpaRepository<SpecsValue, Integer> {
	
	@Query("SELECT s FROM SpecsValue s join s.spec a WHERE a.idSpecs=:idSpec")
	public List<SpecsValue> findBySpec(@Param("idSpec") Integer idSpec);
	
	@Query("SELECT s FROM SpecsValue s join s.spec a WHERE a.idSpecs=:idSpecs and s.idSupplyType=:idSupplyType")
	public List<SpecsValue> findByTypeSupply(@Param("idSpecs") Integer idSpecs, @Param("idSupplyType") Integer idSupplyType);
	
	@Query("SELECT s FROM SpecsValue s join s.spec a WHERE a.idSpecs=:idSpecs and s.idSupplyType=:idSupplyType and s.idSpecsValues!=:idSpecsValues")
	public List<SpecsValue> findByTypeSupplyDifferent(@Param("idSpecs") Integer idSpecs, @Param("idSupplyType") Integer idSupplyType, @Param("idSpecsValues") Integer idSpecsValues);
	
}