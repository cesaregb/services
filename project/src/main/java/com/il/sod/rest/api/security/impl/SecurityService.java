package com.il.sod.rest.api.security.impl;

import com.il.sod.config.Constants;
import com.il.sod.config.JWTSingleton;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.SecurityKeyDto;
import com.il.sod.rest.dto.db.MenuDTO;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/auth", tags = { "auth" })
public class SecurityService extends AbstractServiceMutations {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

	@POST
	@RolesAllowed("BASIC_AUTH")
	@Path("/app/{appId}")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	public Response authClient(@PathParam("appId") String appId) throws SODAPIException {
		SecurityKeyDto dto = new SecurityKeyDto();
		dto.setToken(JWTSingleton.INSTANCE.createJWT(appId, Constants.BASIC_AUTH, 1));
		LOGGER.info("[authClient] info: {}", ConvertUtils.castEntityAsString(dto));
		return ConvertUtils.castEntityAsResponse(dto);
	}
}
