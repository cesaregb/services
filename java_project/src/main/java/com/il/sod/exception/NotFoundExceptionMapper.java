package com.il.sod.exception;

import com.il.sod.rest.dto.GeneralResponseMessage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Component
@Provider
public class NotFoundExceptionMapper extends GeneralMapper implements ExceptionMapper<NotFoundException> {

  @Context
  HttpServletRequest request;

  @Override
  public Response toResponse(NotFoundException ex) {
    long ID = Thread.currentThread().getId();

    logException(ex, request, ID);
    return Response
            .status(Response.Status.NOT_FOUND)
            .entity(new GeneralResponseMessage(ex.getMessage(), String.valueOf(ID), "[missing]", false))
            .type(MediaType.APPLICATION_JSON)
            .build();
  }
}