package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.CashOut;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findByClient(Client client);

  //    @Query("SELECT o FROM Order o join c.clientPaymentInfos cpi WHERE cpi.token=:token")
  List<Order> findByStatus(int status);

  List<Order> findByIdCashOut(int idCashOut);

  @Query("SELECT o FROM Order o Where DATE_FORMAT( o.created, '%Y-%m-%d') = DATE_FORMAT(:date, '%Y-%m-%d')")
  List<Order> findAllByCreateDate(@Param("date") Date date);

  @Query("SELECT o FROM Order o Where DATE_FORMAT(o.created, '%Y-%m-%d') Between DATE_FORMAT(:initDate, '%Y-%m-%d') And DATE_FORMAT(:endDate, '%Y-%m-%d')")
  List<Order> findAllBetweenDates(@Param("initDate") Date initDate, @Param("endDate") Date endDate);
}