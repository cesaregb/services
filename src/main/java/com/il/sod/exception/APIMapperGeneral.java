package com.il.sod.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.il.sod.rest.model.SimpleResponseMessage;

@Provider
public class APIMapperGeneral implements ExceptionMapper<Exception> {
	
	@Override
	public Response toResponse(Exception ex) {
		SimpleResponseMessage ms = new SimpleResponseMessage();
		ms.setMessage(ex.getMessage());
		ms.setCode(SimpleResponseMessage.GENERIC_MESSAGE_ERROR);
		
		return Response.
				status(412).
				entity(ms).
				type(MediaType.APPLICATION_JSON).
				build();
	}
}