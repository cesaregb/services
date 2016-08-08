package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
    List<Order> findByClient(Client client);
    
    List<Order> findByStatus(int status);
    
}