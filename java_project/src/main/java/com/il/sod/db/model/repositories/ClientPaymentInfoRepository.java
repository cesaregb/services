package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ClientPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPaymentInfoRepository extends JpaRepository<ClientPaymentInfo, Integer> {
}