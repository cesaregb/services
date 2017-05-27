package com.il.sod.services.utils;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.GenericDBDTO;
import com.il.sod.services.cruds.EntityServicesBase;

import javax.ws.rs.core.Response;

public class ServicesDBHelper {

	EntityServicesBase mCallback;
	
	public static int TYPE_ADDRESS = 0;
	public static int TYPE_PAYMENT_METHOD = 2;
	
//	public interface CallbackHelper {
//		public <T> T getEntity(JpaRepository<T, Integer> repository, Integer id);
//    }
    
	public ServicesDBHelper(EntityServicesBase service){
		mCallback = service;
	}
	
	// **** Client helper methods. 
	public void validateClient(ClientRepository clientRepository, GenericDBDTO dto) throws SODAPIException {
		Client c = mCallback.getEntity(clientRepository, dto.getParentId());
		if (c == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Client not valid");
		}
	}
	
	// **** Client helper methods. 
	public boolean isFirstItem(ClientRepository clientRepository, GenericDBDTO dto, int type) throws SODAPIException {
		Client c = mCallback.getEntity(clientRepository, dto.getParentId());
		if (c != null){
			if (type == TYPE_ADDRESS){ // address 
				return (c.getAddresses().size() == 0);
			}
			if (type == TYPE_PAYMENT_METHOD){ // phoneNumner
				return (c.getClientPaymentInfos().size() == 0);
			}
		}
		return true;
	}

	
}
