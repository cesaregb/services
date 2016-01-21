package com.il.sod.config;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.il.sod.rest.model.SimpleResponseMessage;

//http://howtodoinjava.com/2015/08/19/jersey-rest-security/
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Context
	private ResourceInfo resourceInfo;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	@Override
	public void filter(ContainerRequestContext requestContext)  {
		Method method = resourceInfo.getResourceMethod();
		LOGGER.info("***** AuthenticationFilter");
		System.out.println("method: " + method.getName() + " -- " + method.getClass().getName());
		// Access allowed for all
		if (!method.isAnnotationPresent(PermitAll.class)) {
			
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(getAccessUnauthorizedResponse());
				return;
			}

			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// Fetch authorization header
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			// If no authorization information present; block access
			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(getAccessDeniedResponse());
				return;
			}

			// Get encoded username and password
			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

			// Decode username and password
			String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

			// Split username and password tokens
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();

			// Verifying Username and password
			System.out.println("username: " + username);
			System.out.println("password: " + password);

			// Verify user access
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				// Is user valid?
				if (!isUserAllowed(username, password, rolesSet)) {
					requestContext.abortWith(getAccessDeniedResponse());
					return;
				}
			}
		}
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
		boolean isAllowed = false;
		if (username.equals("user") && password.equals("user")) {
			String userRole = "ADMIN";

			// Step 2. Verify user role
			if (rolesSet.contains(userRole)) {
				isAllowed = true;
			}
		}
		return isAllowed;
	}
	
	public static Response getAccessDeniedResponse() {
		SimpleResponseMessage ms = new SimpleResponseMessage();
		ms.setMessage("Access not granthed");
		ms.setCode(SimpleResponseMessage.GENERIC_MESSAGE_ERROR);
		
		return Response.
				status(Response.Status.FORBIDDEN).
				entity(ms).
				type(MediaType.APPLICATION_JSON).
				build();
	}
	public static Response getAccessUnauthorizedResponse() {
		SimpleResponseMessage ms = new SimpleResponseMessage();
		ms.setMessage("Access not granthed");
		ms.setCode(SimpleResponseMessage.GENERIC_MESSAGE_ERROR);
		
		return Response.
				status(Response.Status.UNAUTHORIZED).
				entity("You cannot access this resource").
				type(MediaType.APPLICATION_JSON).
				build();
	}
	
}