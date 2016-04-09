package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.ClientPaymentInfo;

public interface ClientPaymentInfoRepository extends JpaRepository<ClientPaymentInfo, Integer> {}