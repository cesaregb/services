package com.il.sod.exception;

import com.il.sod.rest.dto.GeneralResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAllowedExceptionMapper extends GeneralMapper implements ExceptionMapper<NotAllowedException> {
  @Context
  HttpServletRequest request;

  @Override
  public Response toResponse(NotAllowedException ex) {
    long ID = Thread.currentThread().getId();

    logException(ex, request, ID);
    return Response
            .status(Response.Status.METHOD_NOT_ALLOWED)
            .entity(new GeneralResponseMessage("Method Not Allowed",
                    String.valueOf(ID), "[missing]", false))
            .type(MediaType.APPLICATION_JSON)
            .build();
  }
}