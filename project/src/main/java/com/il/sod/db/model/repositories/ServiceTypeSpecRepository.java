package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.ServiceTypeSpec;

public interface ServiceTypeSpecRepository extends JpaRepository<ServiceTypeSpec, Integer> {
	
	@Query("SELECT s FROM ServiceTypeSpec s WHERE s.serviceType.idServiceType=:idServiceType")
    public List<ServiceTypeSpec> findByServiceType(@Param("idServiceType") Integer idServiceType);
}