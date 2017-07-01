package com.il.sod.db.dao;

import com.il.sod.db.model.entities.ClientPaymentInfo;

import java.util.List;

public interface IClientPaymentInfoDAO {

  public abstract List<ClientPaymentInfo> findByIdClient(Integer idClient);

}
