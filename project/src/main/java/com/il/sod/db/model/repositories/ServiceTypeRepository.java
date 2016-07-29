package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
	
//	@Query("SELECT s FROM ServiceType s join c.clientPaymentInfos cpi WHERE cpi.token=:token")
//	public List<ServiceType> findByIdOrderType(@Param("idOrderType") Integer idOrderType);
	
}