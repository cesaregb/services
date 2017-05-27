package com.il.sod.db.dao;

import java.util.List;

import com.il.sod.db.model.entities.ClientPaymentInfo;

public interface IClientPaymentInfoDAO {
	
	public abstract List<ClientPaymentInfo> findByIdClient(Integer idClient);
	
}
