package com.il.sod.exception;

import com.il.sod.rest.dto.GeneralResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

@Provider
public class ThrowableMapper extends GeneralMapper implements ExceptionMapper<Throwable> {
	final static Logger LOGGER = LoggerFactory.getLogger(ThrowableMapper.class);
	
	@Context
	HttpServletRequest request;
	
	@Override
	public Response toResponse(Throwable ex) {
		String error = ex.getMessage(); 
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		error = sw.toString(); // stack trace as a string
		String errorMessage = buildErrorMessage(request, error);
		LOGGER.error(errorMessage);
		return Response.
				status(Response.Status.SERVICE_UNAVAILABLE).
				entity(GeneralResponseMessage.getInstance().error().setMessage("Server error, we are working on this sorry!")).
				type(MediaType.APPLICATION_JSON).
				build();
	}
}