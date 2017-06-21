package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Query("SELECT s FROM Service s WHERE s.order.idOrder=:idOrder")
	List<Service> findByOrder(@Param("idOrder")int idOrder);
}