package com.il.sod.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.dao.IClientPaymentInfoDAO;
import com.il.sod.db.model.entities.ClientPaymentInfo;
import com.il.sod.db.model.repositories.ClientPaymentInfoRepository;

@Service
public class ClientPaymentInfoDAO implements IClientPaymentInfoDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	ClientPaymentInfoRepository clientPaymentInfoRepository;

	// This is the other way to generate a custom Query in the dao, not thru the
	// repository but accessing directly the EM.
	public List<ClientPaymentInfo> findByIdClient(Integer idClient) {
		TypedQuery<ClientPaymentInfo> query = em.createQuery(
				"SELECT cpi FROM ClientPaymentInfo cpi WHERE cpi.client.idClient=?1", ClientPaymentInfo.class);
		query.setParameter(1, idClient);
		return query.getResultList();
	}

}
