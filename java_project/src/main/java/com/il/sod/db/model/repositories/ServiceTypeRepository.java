package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
	
//	@Query("SELECT s FROM ServiceType s join c.clientPaymentInfos cpi WHERE cpi.token=:token")
//	public List<ServiceType> findByIdOrderType(@Param("idOrderType") Integer idOrderType);

	@Query("SELECT s FROM ServiceType s WHERE s.calculator=1")
	public List<ServiceType> findAllPublic();

	@Query("SELECT s FROM ServiceType s WHERE s.idServiceType=:idServiceType")
	public List<ServiceType> findById(@Param("idServiceType") int idServiceType);

}