package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.CashOut;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface CashOutRepository extends DeletableRepository<CashOut, Integer>, JpaSpecificationExecutor<CashOut> {

  @Query("SELECT c FROM CashOut c Where DATE_FORMAT( c.created, '%Y-%m-%d') = DATE_FORMAT(:date, '%Y-%m-%d')")
  public List<CashOut> findAllByCreateDate(@Param("date") Date date);

  @Query("SELECT c FROM CashOut c Where DATE_FORMAT(c.created, '%Y-%m-%d') Between DATE_FORMAT(:initDate, '%Y-%m-%d') And DATE_FORMAT(:endDate, '%Y-%m-%d')")
  public List<CashOut> findAllBetweenDates(@Param("initDate") Date initDate, @Param("endDate") Date endDate);

}
