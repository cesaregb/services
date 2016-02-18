package com.il.sod.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.il.sod.rest.dto.GeneralResponseMessage;

@Provider
public class APIMapper extends GeneralMapper implements ExceptionMapper<Exception> {
	final static Logger LOGGER = LoggerFactory.getLogger(APIMapper.class);
	
	@Context
	HttpServletRequest request;
	
	@Override
	public Response toResponse(Exception ex) {
		String error = ex.getMessage(); 
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		error = sw.toString(); // stack trace as a string
		
		String errorMessage = buildErrorMessage(request, error);
		LOGGER.error(errorMessage);
		
		if (ex instanceof SODAPIException) {
			return Response.
					status(((SODAPIException) ex).getStatus()).
					entity(GeneralResponseMessage.getInstance().error().setMessage(ex.getMessage())).
					type(MediaType.APPLICATION_JSON).
					build();
        }else if (ex instanceof NotFoundException) {
        	return Response.
					status(Response.Status.NOT_FOUND).
					entity(GeneralResponseMessage.getInstance().error().setMessage(ex.getMessage())).
					type(MediaType.APPLICATION_JSON).
					build();
        }else{
        	return Response.
					status(Response.Status.SERVICE_UNAVAILABLE).
					entity(GeneralResponseMessage.getInstance().error().setMessage("Server error, we are working on this sorry!")).
					type(MediaType.APPLICATION_JSON).
					build();
        }
	}
}