package com.il.sod.rest.api.security;

import com.il.sod.rest.api.AbstractServiceMutations;

//@RestController
//@RequestMapping(value = "/auth")
//@Produces(MediaType.APPLICATION_JSON)
//@Api(value = "/auth", tags = { "auth" })
public class SecurityService extends AbstractServiceMutations {
	
//	private final static Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);
//
//	@RequestMapping(method = RequestMethod.POST)
//	@RolesAllowed("BASIC_AUTH")
//	@RequestMapping(value = "/app/{appId}")
//	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
//	public Response authClient(@PathVariable("appId") String appId) throws SODAPIException {
//		SecurityKeyDto dto = new SecurityKeyDto();
//		dto.setToken(JWTSingleton.INSTANCE.createJWT(appId, Constants.BASIC_AUTH, 1));
//		LOGGER.info("[authClient] info: {}", ConvertUtils.castEntityAsString(dto));
//		return ConvertUtils.castEntityAsResponse(dto);
//	}
}
