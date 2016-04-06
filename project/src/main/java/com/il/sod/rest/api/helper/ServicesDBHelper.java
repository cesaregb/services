package com.il.sod.rest.api.helper;

import javax.ws.rs.core.Response;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractService;
import com.il.sod.rest.dto.GenericDBDTO;

public class ServicesDBHelper {
	
	AbstractService mCallback;
	
//	public interface CallbackHelper {
//		public <T> T getEntity(JpaRepository<T, Integer> repository, Integer id);
//    }
    
	public ServicesDBHelper(AbstractService service){
		mCallback = service;
	}
	
	// **** Client helper methods. 
	public void validateClient(ClientRepository clientRepository, GenericDBDTO dto) throws SODAPIException {
		Client c = mCallback.getEntity(clientRepository, dto.getParentId());
		if (c == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Client not valid");
		}
	}

	
}
