package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByClient(Client client);
}