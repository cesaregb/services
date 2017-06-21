package com.il.sod.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.il.sod.rest.dto.GeneralResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProcessingExceptionMapper extends GeneralMapper implements ExceptionMapper<JsonProcessingException> {
	@Context
	HttpServletRequest request;
	
	@Override
	public Response toResponse(JsonProcessingException ex) {
		long ID = Thread.currentThread().getId();

		logException(ex, request, ID);
		return Response
				.status(Response.Status.SERVICE_UNAVAILABLE)
				.entity(new GeneralResponseMessage("Server error, we are working on this sorry!",
						String.valueOf(ID), "[missing]", false))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}