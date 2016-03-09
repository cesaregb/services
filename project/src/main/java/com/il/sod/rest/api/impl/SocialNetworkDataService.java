package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.SocialNetworkData;
import com.il.sod.db.model.repositories.SocialNetworkDataRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SocialNetworkDataDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/social-network-data")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/social-network-data", tags = { "clients" })
public class SocialNetworkDataService extends AbstractServiceMutations {
	@Autowired
	SocialNetworkDataRepository socialNetworkDataRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = SocialNetworkDataDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveSocialNetworkData(SocialNetworkDataDTO dto) throws SODAPIException {
		try {
			SocialNetworkData entity = converter.map(dto, SocialNetworkData.class);
			this.saveEntity(socialNetworkDataRepository, entity);
			dto = converter.map(entity, SocialNetworkDataDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = SocialNetworkDataDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSocialNetworkData(SocialNetworkDataDTO dto) throws SODAPIException {
		try {
			SocialNetworkData entity = converter.map(dto, SocialNetworkData.class);
			this.updateEntity(socialNetworkDataRepository, entity);
			dto = converter.map(entity, SocialNetworkDataDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = SocialNetworkDataDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteSocialNetworkData(SocialNetworkDataDTO dto) throws SODAPIException {
		try {
			SocialNetworkData entity = converter.map(dto, SocialNetworkData.class);
			this.deleteEntity(socialNetworkDataRepository, entity.getIdSocialNetworkData());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = SocialNetworkDataDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSocialNetworkDataList() throws SODAPIException {
		List<SocialNetworkData> rentityList = this.getEntityList(socialNetworkDataRepository);
		List<SocialNetworkDataDTO> list = rentityList.stream().map((i) -> {
			SocialNetworkDataDTO dto = converter.map(i, SocialNetworkDataDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
