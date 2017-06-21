package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
    List<Order> findByClient(Client client);

//    @Query("SELECT o FROM Order o join c.clientPaymentInfos cpi WHERE cpi.token=:token")
    List<Order> findByStatus(int status);

	List<Order> findByIdCashOut(int idCashOut);
}