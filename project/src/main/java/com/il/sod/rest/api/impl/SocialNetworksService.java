package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.SocialNetwork;
import com.il.sod.db.model.repositories.SocialNetworkRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SocialNetworkDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/social-networks")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/social-networks", tags = { "clients" })
public class SocialNetworksService extends AbstractServiceMutations {
	@Autowired
	SocialNetworkRepository socialNetworkRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = SocialNetworkDTO.class)
	public Response saveSocialNetwork(SocialNetworkDTO dto) throws SODAPIException {
		try {
			SocialNetwork entity = converter.map(dto, SocialNetwork.class);
			this.saveEntity(socialNetworkRepository, entity);
			dto = converter.map(entity, SocialNetworkDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = SocialNetworkDTO.class)
	public Response updateSocialNetwork(SocialNetworkDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(SocialNetworkDTO dto) throws SODAPIException {
		try {
			SocialNetwork entity = converter.map(dto, SocialNetwork.class);
			this.updateEntity(socialNetworkRepository, entity);
			dto = converter.map(entity, SocialNetworkDTO.class);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = SocialNetworkDTO.class)
	public Response deleteSocialNetwork(SocialNetworkDTO dto) throws SODAPIException {
		try {
			SocialNetwork entity = converter.map(dto, SocialNetwork.class);
			this.deleteEntity(socialNetworkRepository, entity.getIdSocialNetworks());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = SocialNetworkDTO.class, responseContainer = "List")
	public Response getSocialNetworkList() throws SODAPIException {
		List<SocialNetwork> rentityList = this.getEntityList(socialNetworkRepository);
		List<SocialNetworkDTO> list = rentityList.stream().map((i) -> {
			SocialNetworkDTO dto = converter.map(i, SocialNetworkDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
