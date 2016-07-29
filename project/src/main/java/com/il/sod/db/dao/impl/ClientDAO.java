package com.il.sod.db.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;

@Service
public class ClientDAO{
	
	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> findByEmail(String email){
		return clientRepository.findByEmail(email);
	}

	public List<Client> findByToken(String token) {
		return clientRepository.findByToken(token);
	}
	
	public List<Client> findByLoginID(String loginId) {
		return clientRepository.findByLoginID(loginId);
	}
	
	public List<Client> findByAddress(Integer idAddress) {
		return clientRepository.findByAddress(idAddress);
	}
	
	public List<Client> findByPhone(String phone) {
		return clientRepository.findByPhone("%" + phone + "%");
	}
}
