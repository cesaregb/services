package com.il.sod.config.jersey;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
	final static Logger LOGGER = LoggerFactory.getLogger(CustomLoggingFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("User: ").append(requestContext.getSecurityContext().getUserPrincipal() == null ? "unknown"
				: requestContext.getSecurityContext().getUserPrincipal());
		sb.append(" - Path: ").append(requestContext.getUriInfo().getPath());
		sb.append(" - Header: ").append(requestContext.getHeaders());
		sb.append(" - Entity: ").append(getEntityBody(requestContext));
		
		LOGGER.info("HTTP REQUEST: " + sb.toString());
	}

	private String getEntityBody(ContainerRequestContext requestContext) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = requestContext.getEntityStream();

		final StringBuilder b = new StringBuilder();
		try {
			ReaderWriter.writeTo(in, out);

			byte[] requestEntity = out.toByteArray();
			if (requestEntity.length == 0) {
				b.append("").append("\n");
			} else {
				b.append(new String(requestEntity)).append("\n");
			}
			requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));

		} catch (IOException ex) {
			LOGGER.error("Error getting body from request: " + ex.getMessage());
		}
		return b.toString();
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("Header: ").append(responseContext.getHeaders());
		sb.append(" - Entity: ").append(responseContext.getEntity());
		LOGGER.info("HTTP RESPONSE : " + sb.toString());
	}
}
