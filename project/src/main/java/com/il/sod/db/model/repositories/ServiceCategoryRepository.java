package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ServiceCategory;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
	
	@Query("SELECT c FROM Client c join c.clientPaymentInfos cpi WHERE cpi.token=:token")
    public List<Client> findByToken(@Param("token") String token);
	
	
}