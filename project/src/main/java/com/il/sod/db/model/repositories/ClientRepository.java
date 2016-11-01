package com.il.sod.db.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.il.sod.db.model.entities.Client;

public interface ClientRepository extends DeletableRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
	
	List<Client> findByEmail(String email);
	
	@Query("SELECT c FROM Client c join c.clientPaymentInfos cpi WHERE cpi.token=:token")
    public List<Client> findByToken(@Param("token") String token);
	
	@Query("SELECT c FROM Client c join c.addresses a WHERE a.idAddress=:idAddress")
	public List<Client> findByAddress(@Param("idAddress") Integer idAddress);
	
	@Query("SELECT c FROM Client c join c.phoneNumbers p WHERE p.number LIKE :phone")
	public List<Client> findByPhone(@Param("phone") String phone);
	
	public List<Client> findByLoginID(String loginId);
}
