package com.il.sod.exception;

import com.il.sod.rest.dto.GeneralResponseMessage;
import org.springframework.dao.DataIntegrityViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataIntegrityViolationExceptionMapper extends GeneralMapper implements ExceptionMapper<DataIntegrityViolationException> {
	@Context
	HttpServletRequest request;
	
	@Override
	public Response toResponse(DataIntegrityViolationException ex) {
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