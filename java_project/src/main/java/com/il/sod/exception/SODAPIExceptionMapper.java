package com.il.sod.exception;

import com.il.sod.rest.dto.GeneralResponseMessage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Component
@Provider
public class SODAPIExceptionMapper extends GeneralMapper implements ExceptionMapper<SODAPIException> {

	@Context
	HttpServletRequest request;
	
	@Override
	public Response toResponse(SODAPIException ex) {
		long ID = Thread.currentThread().getId();

		logException(ex, request, ID);
		return Response
				.status(ex.getStatus())
				.entity(new GeneralResponseMessage(ex.getMessage(), String.valueOf(ID), "[missing]", false))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}