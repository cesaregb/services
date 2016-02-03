package com.il.sod.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.il.sod.rest.dto.GeneralResponseMessage;

@Provider
public class APIMapper implements ExceptionMapper<SODAPIException> {
	
	@Override
	public Response toResponse(SODAPIException ex) {
		GeneralResponseMessage ms = new GeneralResponseMessage();
		ms.setMessage(ex.getMessage());
		ms.setCode(GeneralResponseMessage.GENERIC_MESSAGE_ERROR);
		
		return Response.
				status(ex.getStatus()).
				entity(ms).
				type(MediaType.APPLICATION_JSON).
				build();
	}
}