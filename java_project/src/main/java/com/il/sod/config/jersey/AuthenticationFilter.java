package com.il.sod.config.jersey;

import com.il.sod.config.Constants;
import com.il.sod.config.JWTSingleton;
import com.il.sod.rest.dto.GeneralResponseMessage;
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
   *
   * @param requestContext
   * @DenyAll none
   * All endpoints require authentication except for:
   * @PermitAll (any request is served)
   * @RolesAllowed("BASIC_AUTH") (basic authentication is possible)
   * <p>
   * Allowed methods:
   * Endpoints annotated with @PermitAll have access to all requests
   * Endpoint swagger.json
   * OPTIONS requests
   * Request coming from ips [security.ips]
   * Request properly authenticated with the security token.
   * <p>
   * These are not exclusive, it can be authenticated and be coming from a trusted ip
   */

  @Override
  public void filter(ContainerRequestContext requestContext) {
    String reqMethod = requestContext.getMethod();
    Method method = resourceInfo.getResourceMethod();

    // get and log the allowed ips.
    List<String> ips = Constants.envConfig.getStringList("security.ips");
//		LOGGER.info("***** AuthenticationFilter\n ips with access: ");
//		ips.forEach(LOGGER::info);
    LOGGER.info("Request IP Address:" + servletRequest.getRemoteAddr());

    String requestedURLMethod = requestContext.getUriInfo().getPath();
    String requesterIp = servletRequest.getRemoteAddr();
    LOGGER.info("[Request Info] http method: " + reqMethod + " \trequested Method: " + requestedURLMethod + "\tJava Method:" + method.getName());

    // the request is comming from a known ip.
    if (ips.contains(requesterIp)) {
      LOGGER.info("Authentication granted! IP {} in trusted ips", requesterIp);
      return;
    }

    // Get request headers
    final MultivaluedMap<String, String> headers = requestContext.getHeaders();

    // Fetch authorization header
    final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

    StringBuilder basicAuth = new StringBuilder();
    StringBuilder bearerAuth = new StringBuilder();

    // If no authorization information present; block access
    if (authorization != null && !authorization.isEmpty()) {
      // iterate thru auth header, it may contain 2 types.
      for (String s : authorization) {
        if (s.contains(AUTHENTICATION_SCHEME_BASIC)) {
          basicAuth.append(s);
        } else if (s.contains(AUTHENTICATION_SCHEME_BEARER)) {
          bearerAuth.append(s);
        }
      }
    }

    final String authToken = bearerAuth.toString()
            .trim()
            .replaceAll("\\s+", "")
            .replace(AUTHENTICATION_SCHEME_BEARER, "");

    if (JWTSingleton.INSTANCE.isValidToken(authToken)) {
      LOGGER.debug("Authentication granted! Token {} ", authToken);
      return;
    }

    // Access denied for all
    if (method.isAnnotationPresent(DenyAll.class)) {
      LOGGER.error("Authentication DenyAll ");
      requestContext.abortWith(getAccessUnauthorizedResponse());
      return;
    }

    // already authenticated
    System.out.println("********* " + requestedURLMethod + " **** " + requestedURLMethod.toLowerCase().equals("swagger.json"));
    if (method.isAnnotationPresent(PermitAll.class)
            || requestedURLMethod.toLowerCase().equals("swagger.json")
            || reqMethod.toUpperCase().equals("OPTIONS")) {
      LOGGER.debug("Authentication not needed!");
      return;
    }

    // Verify user access
    boolean requireBasicAuth = false;

    Set<String> rolesSet = null;
    RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
    if (rolesAnnotation != null) {
      rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
      requireBasicAuth = rolesSet.contains(Constants.BASIC_AUTH);
    }

    // **  Require basic authentication
    if (requireBasicAuth && basicAuth.length() > 0) {

      // Get encoded username and password
      final String encodedUserPassword = basicAuth.toString().replaceFirst(AUTHENTICATION_SCHEME_BASIC + " ", "");

      // Decode username and password
      String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

      // Split username and password tokens
      final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
      final String username = tokenizer.nextToken();
      final String password = tokenizer.nextToken();

      // Is user valid?
      if (method.isAnnotationPresent(RolesAllowed.class) && !isUserAllowed(username, password, rolesSet)) {
        // we need  to validate if the method requires auth before rejecting the call.
        requestContext.abortWith(getAccessDeniedResponse());
        return;
      } else {
        LOGGER.info("Authentication completed!");
        return;
      }
    }

    //RETURN error for the un-authenticated request.
    requestContext.abortWith(getAccessDeniedResponse());
  }

  /**
   * Validate if user is valid.
   * Only validates the BASIC_AUTH Role.
   *
   * @param username
   * @param password
   * @param rolesSet
   * @return
   */
  private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
    // TODO handle pwd as char-array
    final String systemUser = Constants.envConfig.getString("security.user");
    final String systemPwd = Constants.envConfig.getString("security.password");
    LOGGER.info("[isUserAllowed] ***********************");
    LOGGER.info("\nusername: {} \tpassword: {} ", username, password);
    LOGGER.info("\nsystemUser: {} \tsystemPwd: {} ", systemUser, systemPwd);
    LOGGER.info("[isUserAllowed] ***********************");

    return username.equals(systemUser)
            && password.equals(systemPwd)
            && rolesSet.contains(Constants.BASIC_AUTH);
  }

  private static Response getAccessDeniedResponse() {
    return Response.
            status(Response.Status.UNAUTHORIZED).
            entity(new GeneralResponseMessage(false, "Access not granted")).
            type(MediaType.APPLICATION_JSON).
            build();
  }

  private static Response getAccessUnauthorizedResponse() {
    return Response.
            status(Response.Status.UNAUTHORIZED).
            entity(new GeneralResponseMessage(false, "Access not granted")).
            type(MediaType.APPLICATION_JSON).
            build();
  }
}