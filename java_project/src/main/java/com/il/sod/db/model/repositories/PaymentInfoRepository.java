package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer> {
}