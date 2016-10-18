package com.il.sod.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.il.sod.rest.dto.GeneralResponseMessage;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
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
		}else if (ex instanceof JsonMappingException) {
        	return Response.
					status(Response.Status.BAD_REQUEST).
					entity(GeneralResponseMessage.getInstance().error().setMessage("Error parsing json")).
					type(MediaType.APPLICATION_JSON).
					build();
        }else if(ex instanceof JsonParseException){
        	return Response.
					status(Response.Status.BAD_REQUEST).
					entity(GeneralResponseMessage.getInstance().error().setMessage("Error parsing json")).
					type(MediaType.APPLICATION_JSON).
					build();
        }else if(ex instanceof MessageBodyProviderNotFoundException){
        	return Response.
        			status(Response.Status.BAD_REQUEST).
        			entity(GeneralResponseMessage.getInstance().error().setMessage("Error parsing json")).
        			type(MediaType.APPLICATION_JSON).
        			build();
        }else if (ex instanceof NotFoundException) {
        	return Response.
        			status(Response.Status.NOT_FOUND).
        			entity(GeneralResponseMessage.getInstance().error().setMessage("Resource not found")).
        			type(MediaType.APPLICATION_JSON).
        			build();
        }else if (ex instanceof UnrecognizedPropertyException) {
        	return Response.
        			status(Response.Status.BAD_REQUEST).
        			entity(GeneralResponseMessage.getInstance().error().setMessage("Error parsing json")).
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