package com.il.sod.rest.dto.db;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cesaregb on 6/3/17.
 */
@Data
public class CashOutDTO extends DeletableDTO {
  private int idCashOut;
  private Timestamp created;
  private int user;
  private double subtotal;
  private double pending;
  private double total;
  private double discount;
  private List<Integer> orders;

}
