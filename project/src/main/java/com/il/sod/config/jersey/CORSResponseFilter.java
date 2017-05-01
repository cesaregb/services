package com.il.sod.config.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class CORSResponseFilter implements ContainerResponseFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(CORSResponseFilter.class);
	
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		LOGGER.info("***** CORS Filter");
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, PUT, OPTIONS");
		headers.add("Access-Control-Max-Age", "3600");
		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, api_key, Authorization");
	}
	
}
