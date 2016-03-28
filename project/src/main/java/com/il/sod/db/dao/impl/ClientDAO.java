package com.il.sod.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.dao.IClientDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;

@Service
public class ClientDAO implements IClientDAO{
	
	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> findByEmail(String email){
		return clientRepository.findByEmail(email);
	}

	@Override
	public List<Client> findByToken(String token) {
		return clientRepository.findByToken(token);
	}

}
