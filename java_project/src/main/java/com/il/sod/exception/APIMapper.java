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
public class APIMapper extends GeneralMapper implements ExceptionMapper<Exception> {

  @Context
  HttpServletRequest request;

  @Override
  public Response toResponse(Exception ex) {
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