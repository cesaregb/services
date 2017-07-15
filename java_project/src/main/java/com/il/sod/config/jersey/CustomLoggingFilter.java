package com.il.sod.config.jersey;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.MDC;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class CustomLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
  @Context
  private ResourceInfo resourceInfo;
  private final static Logger log = getLogger(CustomLoggingFilter.class);
  private long startTime = 0;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    startTime = System.currentTimeMillis();
    String queryParams = logParameters(requestContext.getUriInfo().getQueryParameters());
    queryParams = (StringUtils.isEmpty(queryParams)) ? "NA" : queryParams;
    log.info("Method: '{}' API Endpoint: '{}' QParams: '{}' ",
            requestContext.getMethod(),
            requestContext.getUriInfo().getPath(),
            queryParams);
    log.info("Class : {} ", resourceInfo.getResourceClass().getCanonicalName());
    final String body = getEntityBody(requestContext);
    if (StringUtils.isNoneEmpty(body)) {
      log.info("Entity: {} ", body);
    }
  }

  private String getEntityBody(ContainerRequestContext requestContext) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    InputStream in = requestContext.getEntityStream();
    final StringBuilder stringBuilder = new StringBuilder();
    try {
      ReaderWriter.writeTo(in, out);
      byte[] requestEntity = out.toByteArray();
      if (requestEntity.length > 0) {
        stringBuilder.append(new String(requestEntity)).append("\t");
      }
      requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
    } catch (IOException ignored) {
    }
    return stringBuilder.toString();
  }

  private String logParameters(MultivaluedMap<String, String> lParams) {
    String json = null;
    try {
      json = new ObjectMapper().writeValueAsString(lParams);
    } catch (JsonProcessingException ignore) {
    }
    return json;
  }

  private void logCookie(ContainerRequestContext requestContext){
    Map<String, Cookie> cookies = requestContext.getCookies();
    log.debug("Cookies List");
    for (Map.Entry<String, Cookie> entry : cookies.entrySet()) {
      String key = entry.getKey();
      Cookie value = entry.getValue();
      log.debug("{} = {} ", key, value.toString());
    }
    log.debug("End cookies");
  }

  private void logRequestHeader(ContainerRequestContext requestContext) {
    Iterator<String> iterator;
    log.debug("----Start Header Section of request ----");
    log.debug("Method Type : {}", requestContext.getMethod());
    iterator = requestContext.getHeaders().keySet().iterator();
    while (iterator.hasNext()) {
      String headerName = iterator.next();
      String headerValue = requestContext.getHeaderString(headerName);
      log.debug("Header Name: {}, Header Value :{} ", headerName, headerValue);
    }
    log.debug("----End Header Section of request ----");
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
    log.debug("Response entity: {}", responseContext.getEntity());
    log.debug("Total request execution time : {} milliseconds", executionTime);
    MDC.clear();
  }
}
