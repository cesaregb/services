package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.PaymentInfo;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer> {}