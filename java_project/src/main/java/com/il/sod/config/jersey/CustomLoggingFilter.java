package com.il.sod.config.jersey;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;

public class CustomLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
  final static Logger LOGGER = LoggerFactory.getLogger(CustomLoggingFilter.class);

  @Context
  private ResourceInfo resourceInfo;

  private static final Logger log = LoggerFactory.getLogger(CustomLoggingFilter.class);

  private long startTime = 0;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    startTime = System.currentTimeMillis();
    log.debug("Entering in Resource : /{} ", requestContext.getUriInfo().getPath());
    log.debug("Method Name : {} ", resourceInfo.getResourceMethod().getName());
    log.debug("Class : {} ", resourceInfo.getResourceClass().getCanonicalName());
    logQueryParameters(requestContext);
    logMethodAnnotations();
    logRequestHeader(requestContext);

    //log entity stream...
    String entity = readEntityStream(requestContext);
    if (entity != null && entity.trim().length() > 0) {
      log.debug("Entity Stream : {}", entity);
    }
  }

  private void logQueryParameters(ContainerRequestContext requestContext) {
    for (String name : requestContext.getUriInfo().getPathParameters().keySet()) {
      List<String> obj = requestContext.getUriInfo().getPathParameters().get(name);
      String value = null;
      if (null != obj && obj.size() > 0) {
        value = (String) obj.get(0);
      }
      log.debug("Query Parameter Name: {}, Value :{}", name, value);
    }
  }

  private void logMethodAnnotations() {
    Annotation[] annotations = resourceInfo.getResourceMethod().getDeclaredAnnotations();
    if (annotations != null && annotations.length > 0) {
      log.debug("----Start Annotations of resource ----");
      for (Annotation annotation : annotations) {
        log.debug(annotation.toString());
      }
      log.debug("----End Annotations of resource----");
    }
  }

  private void logRequestHeader(ContainerRequestContext requestContext) {
    Iterator<String> iterator;
    log.debug("----Start Header Section of request ----");
    log.debug("Method Type : {}", requestContext.getMethod());
    iterator = requestContext.getHeaders().keySet().iterator();
    while (iterator.hasNext()) {
      String headerName = (String) iterator.next();
      String headerValue = requestContext.getHeaderString(headerName);
      log.debug("Header Name: {}, Header Value :{} ", headerName, headerValue);
    }
    log.debug("----End Header Section of request ----");
  }

  private String readEntityStream(ContainerRequestContext requestContext) {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    final InputStream inputStream = requestContext.getEntityStream();
    final StringBuilder builder = new StringBuilder();
    try {
      ReaderWriter.writeTo(inputStream, outStream);
      byte[] requestEntity = outStream.toByteArray();
      if (requestEntity.length == 0) {
        builder.append("");
      } else {
        builder.append(new String(requestEntity));
      }
      requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
    } catch (IOException ex) {
      log.debug("----Exception occurred while reading entity stream :{}", ex.getMessage());
    }
    return builder.toString();
  }

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
          throws IOException {
//		String stTime = MDC.get("start-time");
//		if (null == stTime || stTime.length() == 0) {
//			return;
//		}
//		long startTime = Long.parseLong(stTime);
    long executionTime = System.currentTimeMillis() - startTime;
    log.debug("Total request execution time : {} milliseconds", executionTime);
    //clear the context on exit
    MDC.clear();
  }
}
