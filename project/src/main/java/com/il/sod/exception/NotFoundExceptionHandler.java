package com.il.sod.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.il.sod.rest.dto.SimpleResponseMessage;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException ex) {
		SimpleResponseMessage ms = new SimpleResponseMessage();
		ms.setMessage(ex.getMessage());
		ms.setCode(SimpleResponseMessage.GENERIC_MESSAGE_ERROR);
		
		return Response.
				status(Response.Status.NOT_FOUND).
				entity(ms).
				type(MediaType.APPLICATION_JSON).
				build();
	}
}
