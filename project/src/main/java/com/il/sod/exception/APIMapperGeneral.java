package com.il.sod.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.il.sod.rest.dto.GeneralResponseMessage;

@Provider
public class APIMapperGeneral implements ExceptionMapper<Exception> {
	
	@Override
	public Response toResponse(Exception ex) {
		GeneralResponseMessage ms = new GeneralResponseMessage();
		ms.setMessage(ex.getMessage());
		ms.setCode(GeneralResponseMessage.GENERIC_MESSAGE_ERROR);
		
		return Response.
				status(412).
				entity(ms).
				type(MediaType.APPLICATION_JSON).
				build();
	}
}