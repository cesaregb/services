package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.AccessKey;
import com.il.sod.db.model.repositories.AccessKeyRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AccessKeyDTO;
import io.swagger.annotations.ApiOperation;
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
@Path("/access-key")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/access-key", tags = { "clients" })
public class AccessKeyService extends AbstractServiceMutations {
	@Autowired
	AccessKeyRepository accessKeyRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = AccessKeyDTO.class)
	public Response saveAccessKey(AccessKeyDTO dto) throws SODAPIException {
		try {
			AccessKey entity = converter.map(dto, AccessKey.class);
			this.saveEntity(accessKeyRepository, entity);
			dto = converter.map(entity, AccessKeyDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = AccessKeyDTO.class)

	public Response updateAccessKey(AccessKeyDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(AccessKeyDTO dto) throws SODAPIException {
		try {
			AccessKey entity = converter.map(dto, AccessKey.class);
			this.updateEntity(accessKeyRepository, entity);
			dto = converter.map(entity, AccessKeyDTO.class);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = AccessKeyDTO.class)

	public Response deleteAccessKey(AccessKeyDTO dto) throws SODAPIException {
		try {
			AccessKey entity = converter.map(dto, AccessKey.class);
			this.deleteEntity(accessKeyRepository, entity.getIdAccessKey());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = AccessKeyDTO.class, responseContainer = "List")

	public Response getAccessKeyList() throws SODAPIException {
		List<AccessKey> rentityList = this.getEntityList(accessKeyRepository);
		List<AccessKeyDTO> list = rentityList.stream().map((i) -> {
			AccessKeyDTO dto = converter.map(i, AccessKeyDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
