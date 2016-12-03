package com.il.sod.config.jersey;

import com.il.sod.config.Constants;
import com.il.sod.config.JWTSingleton;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
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
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Context
	private ResourceInfo resourceInfo;

	@Context
	private HttpServletRequest servletRequest;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";

	private static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
	private static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";

	/**
	 * Auth filter.
	 * NOTE Endpoints can be:
	 * @DenyAll none
	 * All endpoints require authentication except for:
	 * @PermitAll (any request is served)
	 * @RolesAllowed("BASIC_AUTH") (basic authentication is possible)
	 *
	 *  Allowed methods:
		Endpoints annotated with @PermitAll have access to all requests
		Endpoint swagger.json
		OPTIONS requests
		Request coming from ips [security.ips]
		Request properly authenticated with the security token.

		These are not exclusive, it can be authenticated and be coming from a trusted ip
	 *
	 * @param requestContext
	 */

	@Override
	public void filter(ContainerRequestContext requestContext)  {
		Config envConfig = ConfigFactory.load().getConfig(Constants.COM_IL_SOD_APPLICATION);
		String reqMethod = requestContext.getMethod();
		Method method = resourceInfo.getResourceMethod();

		// get and log the allowed ips.
		List<String> ips = envConfig.getStringList("security.ips");
		LOGGER.info("***** AuthenticationFilter\n ips with access: ");
		ips.forEach(LOGGER::info);
		LOGGER.info("Request IP Address:" + servletRequest.getRemoteAddr());

		String myMethod = requestContext.getUriInfo().getPath();
		String requesterIp = servletRequest.getRemoteAddr();
		LOGGER.info("[Request Info] method: " + reqMethod + " \nmyMethod: " + myMethod + "\nJava Method:" + method.getName());

		// the request is comming from a known ip.
		boolean requestAuthenticated = ips.contains(requesterIp);

		if (requestAuthenticated){
			LOGGER.info("Authentication granted! IP {} in trusted ips", requesterIp);
		}

		// Get request headers
		final MultivaluedMap<String, String> headers = requestContext.getHeaders();

		// Fetch authorization header
		final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

		StringBuilder basicAuth = new StringBuilder();
		StringBuilder bearerAuth = new StringBuilder();

		// If no authorization information present; block access
		if (authorization == null || authorization.isEmpty()) {
			requestContext.abortWith(getAccessDeniedResponse());
			return;
		}else{
			// iterate thru auth header, it may contain 2 types.
			for (String s : authorization) {
				if (s.contains(AUTHENTICATION_SCHEME_BASIC)){
					basicAuth.append(s);
				}else if (s.contains(AUTHENTICATION_SCHEME_BEARER)){
					bearerAuth.append(s);
				}
			}
		}

		final String authToken = bearerAuth.toString().trim().replace(AUTHENTICATION_SCHEME_BEARER,"");

		requestAuthenticated = requestAuthenticated || JWTSingleton.INSTANCE.isValidToken(authToken);

		// Access denied for all
		if (method.isAnnotationPresent(DenyAll.class)) {
			requestContext.abortWith(getAccessUnauthorizedResponse());
			return;
		}

		// already authenticated
		boolean bypassAuth = requestAuthenticated
				|| method.isAnnotationPresent(PermitAll.class)
				|| myMethod.toLowerCase().equals("swagger.json")
				|| reqMethod.toUpperCase().equals("OPTIONS");

		// Verify user access
		boolean requireBasicAuth = false;
		Set<String> rolesSet = null;
		RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
		if (rolesAnnotation != null){
			rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
			requireBasicAuth = rolesSet.contains(Constants.BASIC_AUTH);
		}

		// **  Require basic authentication
		if (requireBasicAuth && (! bypassAuth) ) {

			// Get encoded username and password
			final String encodedUserPassword = basicAuth.toString().replaceFirst(AUTHENTICATION_SCHEME_BASIC + " ", "");

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

			// Is user valid?
			if (method.isAnnotationPresent(RolesAllowed.class) && !isUserAllowed(username, password, rolesSet)) {
				// we need  to validate if the method requires auth before rejecting the call.
				requestContext.abortWith(getAccessDeniedResponse());
				return;
			}
		}
		// no return is required, the jwt creation si handled by the auth/app/{appId} endpoint.
	}

	/**
	 * Validate if user is valid.
	 * Only validates the BASIC_AUTH Role.
	 * @param username
	 * @param password
	 * @param rolesSet
	 * @return
	 */
	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
		return username.equals("user")
				&& password.equals("user")
				&& rolesSet.contains(Constants.BASIC_AUTH);
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