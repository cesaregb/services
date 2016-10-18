package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDAO{
	
	@Autowired
	ClientRepository clientRepository;
	
	public Client findByEmail(String email){
		List<Client> list = clientRepository.findByEmail(email);
		if (list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
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
