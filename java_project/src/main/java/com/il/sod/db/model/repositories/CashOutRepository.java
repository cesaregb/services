package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.CashOut;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CashOutRepository extends DeletableRepository<CashOut, Integer>, JpaSpecificationExecutor<CashOut> {
	

}
