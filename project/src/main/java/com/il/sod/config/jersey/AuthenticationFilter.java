package com.il.sod.config.jersey;

import com.il.sod.config.Constants;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;

//http://howtodoinjava.com/2015/08/19/jersey-rest-security/

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Context
	private ResourceInfo resourceInfo;

	@Context
	private HttpServletRequest servletRequest;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";

	private static final String AUTHENTICATION_SCHEME = "Basic";

	@Override
	public void filter(ContainerRequestContext requestContext)  {
		Config envConfig = ConfigFactory.load().getConfig(Constants.COM_IL_SOD_APPLICATION);
		String reqMethod = requestContext.getMethod();
		Method method = resourceInfo.getResourceMethod();

		List<String> ips = envConfig.getStringList("security.ips");
		LOGGER.info("***** AuthenticationFilter\n ips with access: ");
		ips.forEach(LOGGER::info);
		LOGGER.info("For ips different than above, authentication [user/password] will be required");
		LOGGER.info("Requester IP Address:" + servletRequest.getRemoteAddr());

		String myMethod = requestContext.getUriInfo().getPath();
		String requesterIp = servletRequest.getRemoteAddr();
		boolean ipAllowed = ips.contains(requesterIp);
		LOGGER.info("method: " + reqMethod + " \nmyMethod: " + myMethod + "\nJava Method:" + method.getName());

		// Access allowed for all
		if (!ipAllowed
				&& !method.isAnnotationPresent(PermitAll.class)
				&& !myMethod.toLowerCase().equals("swagger.json")
				&& !reqMethod.toUpperCase().equals("OPTIONS")) {

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
			LOGGER.info("***********************");
			LOGGER.info("username: " + username);
			LOGGER.info("password: " + password);
			LOGGER.info("***********************");

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
		return Response.
				status(Response.Status.UNAUTHORIZED).
				entity(new GeneralResponseMessage(false, "Access not granted")).
				type(MediaType.APPLICATION_JSON).
				build();
	}
	public static Response getAccessUnauthorizedResponse() {
		return Response.
				status(Response.Status.UNAUTHORIZED).
				entity(new GeneralResponseMessage(false, "Access not granted")).
				type(MediaType.APPLICATION_JSON).
				build();
	}
}